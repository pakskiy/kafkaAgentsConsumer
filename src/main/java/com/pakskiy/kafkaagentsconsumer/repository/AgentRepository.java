package com.pakskiy.kafkaagentsconsumer.repository;

import com.pakskiy.kafkaagentsconsumer.entity.AgentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends ReactiveCrudRepository<AgentEntity, Long> {
}
