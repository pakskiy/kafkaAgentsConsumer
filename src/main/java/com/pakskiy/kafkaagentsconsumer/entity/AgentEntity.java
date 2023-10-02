package com.pakskiy.kafkaagentsconsumer.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table(name = "agents")
public class AgentEntity {
    @Id
    private Long id;
    private String body;
}
