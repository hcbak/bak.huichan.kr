import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

let stompClient = null;
let subscription = null;

const connect = (onMessageReceived) => {
  const socketFactory = () => new SockJS(`${import.meta.env.VITE_API_SERVER_URI}/api/v1/web-socket`); // SockJS 객체를 생성하는 팩토리 함수
  stompClient = Stomp.over(socketFactory); // 팩토리 함수 전달
  stompClient.debug = function(str) {};

  stompClient.connect({}, () => {
    subscription = stompClient.subscribe('/api/v1/web-socket/topic/public', (message) => { // 구독할 토픽
      const receivedMessage = JSON.parse(message.body);
      onMessageReceived(receivedMessage); // 메시지 수신 시 콜백 함수 호출
    });
  }, (error) => {
    console.error('WebSocket 연결 실패:', error);
  });
};

const disconnect = () => {
  if (stompClient !== null) {
    if (subscription) {
      subscription.unsubscribe();
    }
    stompClient.disconnect();
  }
};

const sendMessage = (message) => {
  if (stompClient && stompClient.connected) {
    stompClient.send('/api/v1/web-socket/app/public', {}, JSON.stringify(message)); // 메시지 전송
  } else {
    console.error('WebSocket 연결이 되어 있지 않습니다.');
  }
};

export { connect, disconnect, sendMessage };
