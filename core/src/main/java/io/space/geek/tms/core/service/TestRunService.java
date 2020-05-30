package io.space.geek.tms.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestRunService {

//    private AutomationHandbookService automationHandbookService;
//    private TestStoryService testStoryService;
//    private JenkinsApi jenkinsApi;
//    private TestAutomationRunApi testAutomationRunApi;
//    private TestAutomationResultApi testAutomationResultApi;
//
//    public TestRunService(AutomationHandbookService automationHandbookService,
//                          TestStoryService testStoryService,
//                          JenkinsApi jenkinsApi,
//                          TestAutomationRunApi testAutomationRunApi,
//                          TestAutomationResultApi testAutomationResultApi) {
//        this.automationHandbookService = automationHandbookService;
//        this.testStoryService = testStoryService;
//        this.jenkinsApi = jenkinsApi;
//        this.testAutomationRunApi = testAutomationRunApi;
//        this.testAutomationResultApi = testAutomationResultApi;
//    }
//
//    @Value("${jenkins.job}")
//    private String testRunJob;
//
//    private Set<String> forbiddenEnvironments = Sets.newHashSet("prod", "preprod", null);
//
//    public void launchTestRun(TestRunDTO testRunDTO) {
//        TestAutomationRunDTO testRun = getOrCreateTestAutomationRun(testRunDTO);
//
//        log.info("Set test result for testStories");
//        List<Integer> automatedTests = new ArrayList<>();
//        testRunDTO.getTestStories().forEach(id -> {
//            Optional<TestStory> testStoryO = testStoryService.getTestStoryO(id);
//            if (testStoryO.isPresent()) {
//                TestStory testStory = testStoryO.get();
//                archivePreviousResults(testRun.getId(), testStory.getId());
//
//                TestAutomationResultDTO testAutomationResultDTO = getBaseTestAutomationResultDTO(testRun, testStory);
//
//                if (testStory.getAutomated() && isAllowedEnvironment(testRunDTO) &&
//                    (Objects.isNull(testRunDTO.getLaunchAutomatedTests()) || testRunDTO.getLaunchAutomatedTests())) {
//
//                    Optional<AutomationHandbook> handbook = automationHandbookService.getHandbookByTestStoryId(id);
//                    if (handbook.isPresent()) {
//                        automatedTests.add(handbook.get().getTestStoryId());
//                    } else {
//                        testAutomationResultDTO.setStatus(TestStatus.SKIP.getValue());
//                    }
//                } else {
//                    testAutomationResultDTO.setStarted(LocalDateTime.now());
//                }
//                TestAutomationResultDTO savedResultDTO = testAutomationResultApi.createTestResult(testAutomationResultDTO);
//                log.info("Saved automationResultDTO for testStory [{}] with id [{}]", testStory.getId(), savedResultDTO.getId());
//            } else {
//                throw new NotFoundException(String.format("Not found testStory with id [%s]", id));
//            }
//        });
//        if (automatedTests.size() > 0) {
//            log.info("Launch tests on jenkins");
//            List<MultiValueMap<String, String>> jobsParameters = getJobParameters(testRun, automatedTests);
//            jobsParameters.forEach(jobProperties -> jenkinsApi.launchJobWithParameters(testRunJob, jobProperties));
//        }
//    }
//
//    public List<MultiValueMap<String, String>> getJobParameters(TestAutomationRunDTO savedRunDTO, List<Integer> testStoryIds) {
//        log.info("Build list of job parameters map to add to the jenkins jobs query");
//        Map<String, List<String>> repositoriesWithTests = getRepositoriesWithTests(testStoryIds);
//        List<MultiValueMap<String, String>> resultJobsParametersList = new ArrayList<>();
//
//        for (Map.Entry<String, List<String>> repoWithTests : repositoriesWithTests.entrySet()) {
//            MultiValueMap<String, String> jobParams = new LinkedMultiValueMap<>(15);
//
//            if (Objects.nonNull(savedRunDTO.getConfiguration())) {
//                jobParams.add("url", String.format("'%s'", savedRunDTO.getConfiguration().getUrl()));
//                jobParams.add("environment", savedRunDTO.getConfiguration().getEnvironment());
//                if (StringUtils.isNotBlank(savedRunDTO.getConfiguration().getBranchName())) {
//                    jobParams.add("branch_name", savedRunDTO.getConfiguration().getBranchName());
//                }
//                if (Objects.nonNull(savedRunDTO.getConfiguration().getBrowser())) {
//                    jobParams.add("browser", savedRunDTO.getConfiguration().getBrowser());
//                    if ("chrome".equalsIgnoreCase(savedRunDTO.getConfiguration().getBrowser())) {
//                        jobParams.add("chrome_version", savedRunDTO.getConfiguration().getBrowserVersion());
//                    }
//                    if ("firefox".equalsIgnoreCase(savedRunDTO.getConfiguration().getBrowser())) {
//                        jobParams.add("firefox_version", savedRunDTO.getConfiguration().getBrowserVersion());
//                    }
//                }
//            }
//
//            jobParams.add("project", savedRunDTO.getProjectId().toString());
//            jobParams.add("report_run_id", savedRunDTO.getId().toString());
//            jobParams.add("launch", savedRunDTO.getName());
//            jobParams.add("tests", StringUtils.join(repoWithTests.getValue(), ","));
//            jobParams.add("repository", repoWithTests.getKey());
//            jobParams.add("tags", String.format("'%s'", savedRunDTO.getTags()));
//
//            resultJobsParametersList.add(jobParams);
//        }
//
//        return resultJobsParametersList;
//    }
//
//    private AutomationHandbook getAutomationHandbook(Integer testStoryId) {
//        log.info("Get automationHandbook from testStory [{}]", testStoryId);
//        return automationHandbookService.getHandbookByTestStoryId(testStoryId).orElseThrow(
//            ExceptionSupplier.newNotFoundException("Not found automationHandbook by testStoryId [{}]", testStoryId));
//    }
//
//    private List<TestAutomationResultDTO> findLastNotArchivedResult(Long testRunId, Integer testStoryId) {
//        PageRequestDTO pageRequestDTO = PageRequestDTO.createBuilder()
//            .addFilter(new PageFilterDTO.FilterDTO("testRunId", String.valueOf(testRunId), FilterType.EQUAL.getType()))
//            .addFilter(new PageFilterDTO.FilterDTO("testStoryId", String.valueOf(testStoryId), FilterType.EQUAL.getType()))
//            .addFilter(new PageFilterDTO.FilterDTO("archived", String.valueOf(false), FilterType.EQUAL.getType()))
//            .page(0)
//            .pageSize(100)
//            .build();
//
//        return testAutomationResultApi.searchTestResults(pageRequestDTO).getEntities();
//    }
//
//    private void archivePreviousResults(Long testRunId, Integer testStoryId) {
//        List<TestAutomationResultDTO> previousResults = findLastNotArchivedResult(testRunId, testStoryId);
//        if (!previousResults.isEmpty()) {
//            previousResults.forEach(previousResult -> {
//                previousResult.setArchived(Boolean.TRUE);
//                testAutomationResultApi.updateTestResult(previousResult);
//            });
//        }
//    }
//
//    private TestAutomationRunDTO getOrCreateTestAutomationRun(TestRunDTO testRunDTO) {
//        log.info("Get or create testAutomationRun from testRunDTO [{}]", testRunDTO);
//        if (Objects.isNull(testRunDTO.getTestRunId())) {
//            String environment = testRunDTO.getEnvironment();
//            if (Objects.nonNull(testRunDTO.getConfiguration())) {
//                environment = testRunDTO.getConfiguration().getEnvironment();
//            }
//            TestAutomationRunDTO testAutomationRunDTO = TestAutomationRunDTO.builder()
//                .environment(environment)
//                .configuration(testRunDTO.getConfiguration())
//                .name(testRunDTO.getLaunch())
//                .featureId(testRunDTO.getFeatureId())
//                .projectId(testRunDTO.getProject())
//                .manual(testRunDTO.getManual())
//                .started(LocalDateTime.now())
//                .status(TestStatus.STARTED.getValue())
//                .tags(testRunDTO.getTags())
//                .build();
//
//            return testAutomationRunApi.createTestRun(testAutomationRunDTO);
//        }
//        return testAutomationRunApi.getTestRun(testRunDTO.getTestRunId());
//    }
//
//    private Map<String, List<String>> getRepositoriesWithTests(List<Integer> testStoryIds) {
//        Map<String, List<String>> repoTestsMap = new HashMap<>();
//        for (Integer testStoryId : testStoryIds) {
//            AutomationHandbook automationHandbook = getAutomationHandbook(testStoryId);
//            String repository = String.format("wkda/%s", automationHandbook.getGitRepo());
//            String testClassPath = automationHandbook.getTestNameClassPath();
//            repoTestsMap.computeIfAbsent(repository, k -> new ArrayList<>()).add(testClassPath);
//        }
//        return repoTestsMap;
//    }
//
//    private Boolean isAllowedEnvironment(TestRunDTO testRunDTO) {
//        return Objects.nonNull(testRunDTO.getConfiguration()) &&
//            !forbiddenEnvironments.contains(testRunDTO.getConfiguration().getEnvironment());
//    }
//
//    static TestAutomationResultDTO getBaseTestAutomationResultDTO(TestAutomationRunDTO testRunDTO,
//                                                                  TestStory testStory) {
//        return TestAutomationResultDTO.builder()
//            .name(testStory.getTitle())
//            .title(testStory.getTitle())
//            .projectId(testRunDTO.getProjectId())
//            .testRunId(testRunDTO.getId())
//            .testStoryId(testStory.getId())
//            .tags(String.format("%s", testRunDTO.getTags()))
//            .status(TestStatus.CREATED.getValue())
//            .archived(Boolean.FALSE)
//            .build();
//    }
}
