package com.voting.votingsystem.kafka;

import com.voting.votingsystem.sockets.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private static final String TOPIC = "public-chats";
    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;
    public void sendMessage(ChatMessage message) {
        kafkaTemplate.send(TOPIC, message);
    }
}