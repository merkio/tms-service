package io.space.geek.tms.core.controller;

import io.space.geek.tms.commons.client.tms.TestCaseApi;
import io.space.geek.tms.commons.dto.tms.TestCaseDTO;
import io.space.geek.tms.core.service.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class TestCaseController implements TestCaseApi {

    private final TestCaseService testCaseService;

    @Override
    @ResponseStatus(OK)
    public List<TestCaseDTO> getTestCases() {
        return testCaseService.getAllTestCases();
    }

    @Override
    @ResponseStatus(CREATED)
    public TestCaseDTO addTestCase(@Valid @RequestBody TestCaseDTO testCaseDTO) {
        return testCaseService.addTestCase(testCaseDTO);
    }

    @Override
    @ResponseStatus(OK)
    public TestCaseDTO getTestCase(@PathVariable("id") Long id) {
        return testCaseService.getTestCaseDTO(id);
    }

    @Override
    @ResponseStatus(OK)
    public TestCaseDTO updateTestCase(@PathVariable("id") Long id, @Valid @RequestBody TestCaseDTO testCaseDTO) {
        return testCaseService.updateTestCase(id, testCaseDTO);
    }

    @Override
    @ResponseStatus(NO_CONTENT)
    public void deleteTestCase(@PathVariable("id") Long id) {
        testCaseService.deleteTestCase(id);
    }
}
