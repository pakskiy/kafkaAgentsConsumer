package com.pakskiy.kafkaagentsconsumer.controller;

import com.pakskiy.kafkaagentsconsumer.servce.impl.AgentServiceImpl;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    private final AgentServiceImpl agentService;
    private final ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

    @PreDestroy
    private void saveBeforeStopApplication(){
        saveList();
    }

    @KafkaListener(topics = "${spring.kafka.consumer.topic.name}", groupId = "simple")
    public void listenEmails(List<String> messages) {
        messages.parallelStream().forEach(el -> concurrentHashMap.put(UUID.randomUUID().toString(), el));
        if(concurrentHashMap.size() >= 200){
            saveList();
        }
    }

    private void saveList(){
        CompletableFuture.runAsync(() -> {
            agentService.save(new ArrayList<>(concurrentHashMap.values())).subscribe();
        });
    }
}
