package com.pakskiy.kafkaagentsconsumer;

import com.pakskiy.kafkaagentsconsumer.repository.AgentRepository;
import com.pakskiy.kafkaagentsconsumer.servce.impl.AgentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaAgentsConsumerApplication.class)
@ActiveProfiles("test")
@Testcontainers
@RequiredArgsConstructor
class KafkaAgentsConsumerApplicationTests {
    @Autowired
    AgentServiceImpl agentService;

    @Autowired
    AgentRepository agentRepository;

    private static final DockerImageName dockerImageName = DockerImageName.parse("postgres:12.15");

    @Container
    static PostgreSQLContainer<?> postgresqlContainer = (PostgreSQLContainer) new PostgreSQLContainer(dockerImageName)
            .withDatabaseName("agent_consumer")
            .withUsername("postgres")
            .withPassword("123456")
			.withExposedPorts(5432)
			.withReuse(true);

    @BeforeAll
    static void beforeAll() {
        postgresqlContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgresqlContainer.stop();
    }


    @Test
    void test_check_save_data() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        long elCount = 10;
        List<String> messageList = new ArrayList<>();
        for (int i = 0; i < elCount; i++) {
            messageList.add(random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString());
        }

        messageList.parallelStream().forEach(agentService::save);
        Long itemsCount = agentRepository.findAll().count().block();
        assertEquals(itemsCount, elCount);
    }

}
