<script setup>
import { ref, computed, onMounted, onUnmounted, nextTick } from 'vue';
import ChatMessage from '../components/ChatMessage.vue';
import { connect, disconnect, sendMessage } from '../services/chatService';

const chatOpen = ref(false);
const messages = ref([]);
const newMessage = ref('');
const chatMessagesRef = ref(null);
const unreadCount = ref(0);

// 채팅 상자 헤더 클릭 이벤트
const chatBoxHeaderClick = () => {
  chatOpen.value = !chatOpen.value;

  // 읽지 않은 메시지 개수 초기화
  if (chatOpen.value) {
    unreadCount.value = 0;
  }
};

// 채팅 상자가 열리고 닫힐 때 스타일
const chatBoxStyle = computed(() => {
  return {
    bottom: chatOpen.value ? '32px' : '-325px',
    transition: 'bottom 0.7s ease-in',
  };
});

// 메시지 수신 이벤트
const handleMessageReceived = (receivedMessage) => {
  messages.value.push(receivedMessage);

  // 메시지 창이 열려 있지 않으면 읽지 않은 메시지 개수 증가
  if (!chatOpen.value) {
    unreadCount.value++;
  }

  scrollFix();
};

// 스크롤 하단 고정
const scrollFix = async () => {
  await nextTick();
  if (chatMessagesRef.value) {
    chatMessagesRef.value.scrollTop = chatMessagesRef.value.scrollHeight;
  }
};

// 메시지 발신 이벤트
const handleSendMessage = () => {
  if (newMessage.value.trim() !== '') {
    const message = {
      name: '나그네', // 인증 기능 추가 후 미인증: 나그네, 인증: 닉네임?
      message: newMessage.value
    };
    sendMessage(message);
    newMessage.value = '';
  }
};

onMounted(() => {
  connect(handleMessageReceived);
});

onUnmounted(() => {
  disconnect();
});
</script>

<template>
  <!-- 채팅 상자 -->
  <BCard
    header-tag="header"
    footer-tag="footer"
    class="chatBox"
    :style="chatBoxStyle"
    body-class="p-0"
  >
    <!-- 채팅 상자 상단 (아이콘, 닉네임, 읽지 않은 메시지) -->
    <template #header>
      <div @click="chatBoxHeaderClick" style="cursor: pointer; display: flex; align-items: center;">
        <BAvatar class="mx-3" />
        <span class="me-auto">나그네</span>
        <BBadge v-if="unreadCount > 0">{{ unreadCount }}</BBadge>
      </div>
    </template>

    <!-- 채팅 상자 중앙 (메시지) -->
    <div class="chat-messages" ref="chatMessagesRef">
      <ChatMessage v-for="(message, index) in messages" :key="index" :message="message" />
    </div>

    <!-- 채팅 상자 하단 (메시지 입력) -->
    <template #footer>
      <BInputGroup size="sm">
        <BFormInput v-model="newMessage" @keyup.enter="handleSendMessage" />
        <BButton size="sm" @click="handleSendMessage">전송</BButton>
      </BInputGroup>
    </template>
  </BCard>
</template>

<style scoped>
/* 채팅 상자 */
.chatBox {
  position: fixed;
  width: 256px;
  height: 382px;
  right: 32px;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.5);
}

/* 채팅 상자의 입력 상자 선택 시 파란색 그림자 발생 제거 */
.chatBox .form-control:focus {
  box-shadow: none;
}

/* 채팅 상자 중앙 메시지 부분 */
.chat-messages {
  overflow-y: scroll;
  height: 275px;
  max-width: 100%;
}

/* 채팅 상자 스크롤 바 */
.chat-messages::-webkit-scrollbar {
  width: 6px;
}

.chat-messages::-webkit-scrollbar-track {
  border-radius: 4px;
}

.chat-messages::-webkit-scrollbar-thumb {
  background: #e2e2e2;
  border-radius: 4px;
}

.chat-messages::-webkit-scrollbar-thumb:hover {
  background: #555;
}
</style>
