package com.voting.votingsystem.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor

public class KafkaAdministration {
     private final KafkaAdmin kafkaAdmin;

    public boolean checkIfTopicExists(String topicName) throws ExecutionException, InterruptedException {
        AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
        ListTopicsResult topicsResult = adminClient.listTopics();
        Set<String> existingTopics = topicsResult.names().get();
        adminClient.close();
        return existingTopics.contains(topicName);
    }

    public void createANewTopic(String topicName) {
        NewTopic newTopic = TopicBuilder.name(topicName)
                .partitions(3)
                .replicas(1)
                .build();
        AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
        adminClient.createTopics(Collections.singletonList(newTopic));
        adminClient.close();
    }

}
