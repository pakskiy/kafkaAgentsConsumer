package com.pakskiy.kafkaagentsconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class KafkaAgentsConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaAgentsConsumerApplication.class, args);
    }

}
