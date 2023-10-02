package com.pakskiy.kafkaagentsconsumer.servce;

import reactor.core.publisher.Mono;

public interface AgentService {
    Mono<Void> save(String data);
}
