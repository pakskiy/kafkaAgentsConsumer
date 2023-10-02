package com.pakskiy.kafkaagentsconsumer.servce;

import com.pakskiy.kafkaagentsconsumer.entity.AgentEntity;
import reactor.core.publisher.Flux;

import java.util.List;

public interface AgentService {
    Flux<AgentEntity> save(List<String> data);
}
