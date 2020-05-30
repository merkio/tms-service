package io.space.geek.tms.report.service.impl;

import io.space.geek.tms.commons.dto.report.TestRunDTO;
import io.space.geek.tms.commons.util.BeanUtils;
import io.space.geek.tms.report.domain.TestRun;
import io.space.geek.tms.report.repository.TestRunRepository;
import io.space.geek.tms.report.service.ITestRunService;
import io.space.geek.tms.report.util.EntityAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static io.space.geek.tms.commons.exception.ResourceNotFoundException.newResourceNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestRunService implements ITestRunService {

    private final TestRunRepository testRunRepository;
    private final EntityAdapter entityAdapter;

    @Override
    public TestRunDTO createTestRun(@NotNull TestRunDTO testRunDTO) {
        log.info("Create new test run with name [{}]", testRunDTO.getName());
        TestRun savedTestRun = testRunRepository.save(entityAdapter.fromDTO(testRunDTO));
        log.info("Successfully saved test run [{}] with id [{}]", savedTestRun.getName(), savedTestRun.getId());
        return entityAdapter.toDTO(savedTestRun);
    }

    @Override
    public List<TestRunDTO> getAll() {
        log.info("Get all test runs");
        return testRunRepository.findAll().stream()
            .map(entityAdapter::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public TestRunDTO getTestRun(@NotNull String id) {
        log.info("Get test run with id [{}]", id);
        return entityAdapter.toDTO(testRunRepository.findById(id)
                                       .orElseThrow(newResourceNotFoundException("test-run", "id", id)));
    }

    @Override
    public TestRunDTO updateTestRun(@NotNull String id, @NotNull TestRunDTO testRunDTO) {
        log.info("Update test run [{}]", id);
        TestRun currentTestRun = testRunRepository.findById(id)
            .orElseThrow(newResourceNotFoundException("test-run", "id", id));
        BeanUtils.copyNonNullProperties(entityAdapter.fromDTO(testRunDTO), currentTestRun);

        testRunRepository.save(currentTestRun);
        log.info("Successfully updated test run [{}]", id);
        return entityAdapter.toDTO(currentTestRun);
    }

    @Override
    public void deleteTestRun(@NotNull String id) {
        log.info("Delete test run [{}]", id);
        testRunRepository.deleteById(id);
    }
}
