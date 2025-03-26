package kr.huichan.bak.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.huichan.bak.main.dto.PublicMessageRequest;
import kr.huichan.bak.main.dto.PublicMessageResponse;

@Service
public class ChatService {
    
    // 데이터베이스에 메시지 저장
    public void receivedMessage(PublicMessageRequest message) {

    }

    // 데이터베이스에서 최근 메시지 불러오기
    public List<PublicMessageResponse> getPublicMessageList() {

        return null;
    }
}
