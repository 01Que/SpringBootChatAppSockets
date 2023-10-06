package com.quinton.chatApp.websocket;

import com.quinton.chatApp.chat.ChatMessage;
import com.quinton.chatApp.chat.MessageType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@AllArgsConstructor
@Slf4j//for loging info
public class WebSocketEventListener {

    //dependancy injection
    private final SimpMessageSendingOperations messageTemplate;
    @EventListener
    public void handleWebSocketDisconnectEvent(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username != null){
            log.info("user: {}",username," disconnected");
            var chatMessage = ChatMessage.builder()
                                         .type(MessageType.LEAVE)
                                         .sender(username)
                                         .build();
            messageTemplate.convertAndSend("topic/public", chatMessage);
        }
    }
}
