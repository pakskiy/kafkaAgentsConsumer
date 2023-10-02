package com.pakskiy.kafkaagentsconsumer.servce.impl;

import com.pakskiy.kafkaagentsconsumer.entity.AgentEntity;
import com.pakskiy.kafkaagentsconsumer.repository.AgentRepository;
import com.pakskiy.kafkaagentsconsumer.servce.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;

    @Override
    public Mono<Void> save(String data) {
        return agentRepository.save(AgentEntity.builder().body(data).build()).then();
    }
}
