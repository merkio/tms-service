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
//    private final TestResultApi testResultApi;
    private final EntityAdapter entityAdapter;

    public TestCaseDTO addTestCase(TestCaseDTO testCaseDTO) {
        log.info("Add to testStory [{}]", testCaseDTO.getTitle());

        TestCase testStory = entityAdapter.fromDTO(testCaseDTO);

        TestCase savedTestCase = testCaseRepository.saveAndFlush(testStory);
        log.trace("Successfully added to test_stories: [{}]", savedTestCase);
        return entityAdapter.toDTO(savedTestCase);
    }

    public TestCaseDTO updateTestCase(Long id, TestCaseDTO testCaseDTO) {
        log.info("Updating testStory item [{}]", id);
        TestCase testStory = entityAdapter.fromDTO(testCaseDTO);

        TestCase currentTestCase = testCaseRepository.findById(id)
            .orElseThrow(newResourceNotFoundException("TestCase", "id", id));
        BeanUtils.copyNonNullProperties(currentTestCase, testStory);

        TestCase updatedTestCase = testCaseRepository.saveAndFlush(currentTestCase);
        log.info("Successfully updated testStory item: {}", updatedTestCase.getId());

        return entityAdapter.toDTO(updatedTestCase);
    }

    public void deleteTestCase(Long id) {
        log.info("Deleting testStory with id [{}]", id);

        log.info("Remove all testResults for testStoryId [{}]", id);
//        testResultApi.deleteResults(findAllTestResultsWithTestCaseId());

        testCaseRepository.deleteById(id);
        log.info("Successfully removed testStory with id [{}]", id);
    }

    public TestCaseDTO getTestCaseDTO(Long id) {
        TestCase testStory = this.testCaseRepository.findById(id)
            .orElseThrow(newResourceNotFoundException("TestCase", "id", id));
        return entityAdapter.toDTO(testStory);
    }

    public List<TestCaseDTO> updateTestCaseBatch(List<TestCaseDTO> testStoryBatch) {
        log.info("Updating [{}] test stories", testStoryBatch.size());
        List<TestCaseDTO> updatedTestCaseBatch = testStoryBatch.stream()
            .map(testCaseDTO -> updateTestCase(testCaseDTO.getId(), testCaseDTO))
            .collect(Collectors.toList());

        log.info("Successfully updated [{}] test stories", updatedTestCaseBatch.size());
        return updatedTestCaseBatch;
    }

    public List<TestCase> getAllTestCaseByAcceptanceCriteriaId(Long acceptanceCriteriaId) {
        log.info("Get all test stories with acceptanceCriteriaId [{}]", acceptanceCriteriaId);
        return testCaseRepository.findByAcceptanceCriteriaId(acceptanceCriteriaId);
    }

    public void deleteTestStoriesWithAcceptanceCriteriaId(Long acceptanceCriteriaId) {
        log.info("Deleting testStories with acceptanceCriteriaId [{}]", acceptanceCriteriaId);
        List<TestCase> testStoryList = testCaseRepository.findByAcceptanceCriteriaId(acceptanceCriteriaId);
        testStoryList.forEach(ts -> deleteTestCase(ts.getId()));
        log.info("Successfully removed all testStories with acceptanceCriteriaId [{}]", acceptanceCriteriaId);
    }

    public Integer countTestCaseByFeatureId(Long featureId) {
        log.info("Count test stories by featureId [{}]", featureId);
        return testCaseRepository.countByFeatureId(featureId);
    }

    public Long countTestStoriesByFeatureIdAndAutomated(Long featureId, Boolean automated) {
        log.info("Count automated test stories by featureId [{}]", featureId);
        return testCaseRepository.countByFeatureIdAndAutomated(featureId, automated);
    }

    public List<TestCaseDTO> getAllTestCases() {
        return testCaseRepository.findAll().stream()
            .map(entityAdapter::toDTO)
            .collect(Collectors.toList());
    }
}
