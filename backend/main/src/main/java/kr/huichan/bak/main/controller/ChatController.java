package kr.huichan.bak.main.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.huichan.bak.main.dto.PublicMessageRequest;
import kr.huichan.bak.main.dto.PublicMessageResponse;
import kr.huichan.bak.main.service.ChatService;

@RestController
@RequestMapping("/api/v1/chat")
@Tag(name="Chat", description = "채팅")
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("public")
    @SendTo("/api/v1/web-socket/topic/public")
    public PublicMessageRequest publicChat(PublicMessageRequest message) {
        
        chatService.receivedMessage(message);
        
        return message;
    }

    @GetMapping("public")
    public ResponseEntity<List<PublicMessageResponse>> getPublicChatList() {

        return ResponseEntity.ok(chatService.getPublicMessageList());
    }

    // 생성자 주입
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
}
