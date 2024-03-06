package com.voting.votingsystem.Controllers.websocketscontroller;


import com.voting.votingsystem.kafka.KafkaConsumerService;
import com.voting.votingsystem.kafka.KafkaProducerService;
import com.voting.votingsystem.sockets.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final KafkaProducerService kafkaProducerServices;
    private final KafkaConsumerService kafkaConsumerServices;

    @MessageMapping("/chat/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage
    ) {
        return chatMessage;
    }

    @MessageMapping("/chat/addUser")
    public void addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {

        Objects.requireNonNull(headerAccessor.getSessionAttributes(), "Header accessor was null").put("username", chatMessage.getSender());
        kafkaProducerServices.sendMessage(chatMessage);
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public void handleChatMessage(@Payload ChatMessage message) {

        kafkaProducerServices.sendMessage(message);
    }

    @GetMapping("/api/chat")
    public List<ChatMessage> getChatMessages() {

        return kafkaConsumerServices.getChatMessages();
    }

}
