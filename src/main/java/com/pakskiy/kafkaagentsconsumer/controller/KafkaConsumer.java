package com.pakskiy.kafkaagentsconsumer.controller;

import com.pakskiy.kafkaagentsconsumer.servce.impl.AgentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    private final AgentServiceImpl agentService;

    @KafkaListener(
            topics = "${spring.kafka.consumer.topic.name}",
            groupId = "simple",
            concurrency = "3")
    public void listenEmails(List<String> messages) {
        CompletableFuture.runAsync(() -> {
            agentService.save(messages).subscribe();
        });
    }
}
