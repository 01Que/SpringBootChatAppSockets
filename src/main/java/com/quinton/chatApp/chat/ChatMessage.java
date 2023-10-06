package com.quinton.chatApp.chat;


import com.quinton.chatApp.chat.MessageType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {

    private String content, sender;
    private MessageType type;
}
