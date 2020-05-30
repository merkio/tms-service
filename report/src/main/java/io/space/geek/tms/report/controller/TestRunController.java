package io.space.geek.tms.report.controller;

import io.space.geek.tms.commons.client.report.TestRunApi;
import io.space.geek.tms.commons.dto.report.TestRunDTO;
import io.space.geek.tms.report.service.ITestRunService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class TestRunController implements TestRunApi {

    private final ITestRunService ITestRunService;

    @Override
    @ResponseStatus(OK)
    public List<TestRunDTO> getAll() {
        return ITestRunService.getAll();
    }

    @Override
    @ResponseStatus(OK)
    public TestRunDTO getTestRun(@PathVariable("id") String id) {
        return ITestRunService.getTestRun(id);
    }

    @Override
    public TestRunDTO createTestRun(@RequestBody TestRunDTO testRunDTO) {
        return ITestRunService.createTestRun(testRunDTO);
    }

    @Override
    @ResponseStatus(OK)
    public TestRunDTO updateTestRun(@PathVariable("id") String id,
                                    @Valid @RequestBody TestRunDTO testRunDTO) {
        return ITestRunService.updateTestRun(id, testRunDTO);
    }

    @Override
    @ResponseStatus(NO_CONTENT)
    public void deleteTestRun(@PathVariable("id") String id) {
        ITestRunService.deleteTestRun(id);
    }
}
