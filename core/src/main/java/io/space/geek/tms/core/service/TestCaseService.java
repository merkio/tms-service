package io.space.geek.tms.core.service;

import io.space.geek.tms.commons.dto.tms.TestCaseDTO;
import io.space.geek.tms.commons.util.BeanUtils;
import io.space.geek.tms.core.domain.TestCase;
import io.space.geek.tms.core.repository.TestCaseRepository;
import io.space.geek.tms.core.util.EntityAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.space.geek.tms.commons.exception.ResourceNotFoundException.newResourceNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestCaseService {

    private final TestCaseRepository testCaseRepository;
    private final EntityAdapter entityAdapter;

    public TestCaseDTO addTestCase(TestCaseDTO testCaseDTO) {
        log.info("Add to testCase [{}]", testCaseDTO.getTitle());

        TestCase testCase = entityAdapter.fromDTO(testCaseDTO);

        TestCase savedTestCase = testCaseRepository.saveAndFlush(testCase);
        log.trace("Successfully added to test_stories: [{}]", savedTestCase);
        return entityAdapter.toDTO(savedTestCase);
    }

    public TestCaseDTO updateTestCase(Long id, TestCaseDTO testCaseDTO) {
        log.info("Updating testCase item [{}]", id);
        TestCase testCase = entityAdapter.fromDTO(testCaseDTO);

        TestCase currentTestCase = testCaseRepository.findById(id)
            .orElseThrow(newResourceNotFoundException("TestCase", "id", id));
        BeanUtils.copyNonNullProperties(currentTestCase, testCase);

        TestCase updatedTestCase = testCaseRepository.saveAndFlush(currentTestCase);
        log.info("Successfully updated testCase item: {}", updatedTestCase.getId());

        return entityAdapter.toDTO(updatedTestCase);
    }

    public void deleteTestCase(Long id) {
        log.info("Deleting testCase with id [{}]", id);

        log.info("Remove all testResults for testCaseId [{}]", id);

        testCaseRepository.deleteById(id);
        log.info("Successfully removed testCase with id [{}]", id);
    }

    public TestCaseDTO getTestCaseDTO(Long id) {
        TestCase testCase = this.testCaseRepository.findById(id)
            .orElseThrow(newResourceNotFoundException("TestCase", "id", id));
        return entityAdapter.toDTO(testCase);
    }

    public List<TestCaseDTO> updateTestCaseBatch(List<TestCaseDTO> testCaseBatch) {
        log.info("Updating [{}] test stories", testCaseBatch.size());
        List<TestCaseDTO> updatedTestCaseBatch = testCaseBatch.stream()
            .map(testCaseDTO -> updateTestCase(testCaseDTO.getId(), testCaseDTO))
            .collect(Collectors.toList());

        log.info("Successfully updated [{}] test stories", updatedTestCaseBatch.size());
        return updatedTestCaseBatch;
    }

    public List<TestCase> getAllTestCaseByAcceptanceCriteriaId(Long acceptanceCriteriaId) {
        log.info("Get all test stories with acceptanceCriteriaId [{}]", acceptanceCriteriaId);
        return testCaseRepository.findByAcceptanceCriteriaId(acceptanceCriteriaId);
    }

    public Integer countTestCaseByFeatureId(Long featureId) {
        log.info("Count test stories by featureId [{}]", featureId);
        return testCaseRepository.countByFeatureId(featureId);
    }

    public List<TestCaseDTO> getAllTestCases() {
        return testCaseRepository.findAll().stream()
            .map(entityAdapter::toDTO)
            .collect(Collectors.toList());
    }
}
