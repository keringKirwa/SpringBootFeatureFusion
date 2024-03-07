package com.voting.votingsystem.config.kafkaconfig;


import com.voting.votingsystem.Entities.Message;
import com.voting.votingsystem.Entities.Progress;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * in this  program here , we are setting up 2 kafka templates , one for producinng messges of type  Message
 * and the other for producing messages of type  Progress.
 */

@Configuration
public class KafkaProducerConfig {

    private Map<String, Object> producerConfigs() {
        Map<String, Object> config = new HashMap<>();

        String KAFKA_BOOTSTRAP_SERVER = "kafka:29092";

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,KAFKA_BOOTSTRAP_SERVER);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return config;
    }

    private <T> ProducerFactory<String, T> createProducerFactory(Class<T> messageClass) {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public ProducerFactory<String, Message> messageProducerFactory() {
        return createProducerFactory(Message.class);
    }

    @Bean
    public KafkaTemplate<String, Message> messageKafkaTemplate() {
        return new KafkaTemplate<>(messageProducerFactory());
    }

    @Bean
    public ProducerFactory<String, Progress> progressProducerFactory() {
        return createProducerFactory(Progress.class);
    }

    @Bean
    public KafkaTemplate<String, Progress> progressKafkaTemplate() {
        return new KafkaTemplate<>(progressProducerFactory());
    }
}

