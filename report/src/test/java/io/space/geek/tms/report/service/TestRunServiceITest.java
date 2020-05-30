package io.space.geek.tms.report.service;

import io.space.geek.tms.commons.dto.report.TestRunConfigurationDTO;
import io.space.geek.tms.commons.dto.report.TestRunDTO;
import io.space.geek.tms.report.domain.TestRun;
import io.space.geek.tms.report.repository.TestRunRepository;
import io.space.geek.tms.report.util.EntityAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestRunServiceITest {

    @Autowired
    private ITestRunService ITestRunService;

    @Autowired
    private TestRunRepository testRunRepository;

    @Autowired
    private EntityAdapter entityAdapter;

    @BeforeEach
    void setUp() {
        testRunRepository.deleteAll();
    }

    @Test
    void getTestRun() {
        TestRun testRun = TestRun.builder()
            .name("Name")
            .results(Collections.emptySet())
            .configuration(TestRunConfigurationDTO.builder().environment("qa").build())
            .build();

        testRunRepository.save(testRun);

        TestRunDTO testRunDTO = ITestRunService.getTestRun(testRun.getId());

        assertNotNull(testRunDTO.getId());
        assertEquals(testRun.getName(), testRunDTO.getName());
        assertNotNull(testRunDTO.getConfiguration());
        assertEquals(testRun.getConfiguration().getEnvironment(), testRunDTO.getConfiguration().getEnvironment());
    }

    @Test
    void createTestRun() {
        TestRunDTO testRunDTO = TestRunDTO.builder()
            .name("Name")
            .results(Collections.emptySet())
            .configuration(TestRunConfigurationDTO.builder().environment("qa").build())
            .build();

        TestRunDTO savedTestRun = ITestRunService.createTestRun(testRunDTO);

        assertNotNull(savedTestRun.getId());
        assertEquals(testRunDTO.getName(), savedTestRun.getName());
        assertNotNull(savedTestRun.getConfiguration());
        assertEquals(testRunDTO.getConfiguration().getEnvironment(), savedTestRun.getConfiguration().getEnvironment());
    }

    @Test
    void updateTestRun() {
        TestRunDTO testRunDTO = TestRunDTO.builder()
            .name("Name")
            .results(Collections.emptySet())
            .configuration(TestRunConfigurationDTO.builder().environment("qa").build())
            .build();

        TestRun savedTestRun = testRunRepository.save(entityAdapter.fromDTO(testRunDTO));
        savedTestRun.setDescription("Description");
        savedTestRun.setName("New Name");

        TestRunDTO updatedTestRun = ITestRunService.updateTestRun(savedTestRun.getId(), entityAdapter.toDTO(savedTestRun));

        assertNotNull(updatedTestRun.getId());
        assertEquals(savedTestRun.getName(), updatedTestRun.getName());
        assertEquals(savedTestRun.getDescription(), updatedTestRun.getDescription());
        assertNotNull(updatedTestRun.getConfiguration());
        assertEquals(savedTestRun.getConfiguration().getEnvironment(), updatedTestRun.getConfiguration().getEnvironment());
    }

    @Test
    void deleteTestRun() {
        TestRunDTO testRunDTO = TestRunDTO.builder()
            .name("Name")
            .results(Collections.emptySet())
            .configuration(TestRunConfigurationDTO.builder().environment("qa").build())
            .build();

        TestRun savedTestRun = testRunRepository.save(entityAdapter.fromDTO(testRunDTO));

        ITestRunService.deleteTestRun(savedTestRun.getId());
        Optional<TestRun> testRunO = testRunRepository.findById(savedTestRun.getId());

        assertFalse(testRunO.isPresent());
    }
}
