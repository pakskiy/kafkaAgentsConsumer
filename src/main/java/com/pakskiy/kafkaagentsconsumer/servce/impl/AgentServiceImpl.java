package com.pakskiy.kafkaagentsconsumer.servce.impl;

import com.pakskiy.kafkaagentsconsumer.entity.AgentEntity;
import com.pakskiy.kafkaagentsconsumer.repository.AgentRepository;
import com.pakskiy.kafkaagentsconsumer.servce.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;

    public Flux<AgentEntity> save(List<String> data) {
        return agentRepository.saveAll(data.stream().map(e -> AgentEntity.builder().body(e).build()).toList());
    }
}
