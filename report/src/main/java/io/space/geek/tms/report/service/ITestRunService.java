package io.space.geek.tms.report.service;

import io.space.geek.tms.commons.dto.report.TestRunDTO;

import java.util.List;

public interface ITestRunService {

    TestRunDTO createTestRun(TestRunDTO testRunDTO);

    List<TestRunDTO> getAll();

    TestRunDTO getTestRun(String id);

    TestRunDTO updateTestRun(String id, TestRunDTO testRunDTO);

    void deleteTestRun(String id);
}
