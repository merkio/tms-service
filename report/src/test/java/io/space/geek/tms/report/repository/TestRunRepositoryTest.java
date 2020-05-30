package io.space.geek.tms.report.repository;

import io.space.geek.tms.commons.dto.report.TestRunConfigurationDTO;
import io.space.geek.tms.report.domain.TestRun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
public class TestRunRepositoryTest {

    @Autowired
    private TestRunRepository testRunRepository;

    @BeforeEach
    void setUp() {
        testRunRepository.deleteAll();
    }

    @Test
    void createTestRun() {
        TestRun testRun = TestRun.builder()
            .name("Name")
            .manual(true)
            .configuration(TestRunConfigurationDTO.builder().environment("qa").platform("linux").build())
            .build();

        TestRun saveTestRun = testRunRepository.save(testRun);

        assertNotNull(saveTestRun.getId());
        assertEquals(testRun.getName(), saveTestRun.getName());
    }
}
