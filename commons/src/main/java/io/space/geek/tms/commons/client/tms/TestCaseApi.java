package io.space.geek.tms.commons.client.tms;

import io.space.geek.tms.commons.dto.tms.TestCaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(value = "test-case-api", url = "http://localhost:8181")
@ApiModel(description = "Operations with test cases")
public interface TestCaseApi {

    @ApiOperation("Get all test cases")
    @ApiResponse(code = 200, message = "get all test cases")
    @ResponseStatus(OK)
    @GetMapping(
        value = "/tms/test_cases",
        produces = APPLICATION_JSON_VALUE)
    List<TestCaseDTO> getTestCases();

    @ApiOperation("Add a new testCase")
    @ApiResponse(code = 200, message = "add a new testCase")
    @ResponseStatus(OK)
    @PostMapping(
        value = "/tms/test_cases",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    TestCaseDTO addTestCase(@Valid @RequestBody TestCaseDTO testCaseDTO);

    @ApiOperation("Update testCase")
    @ApiResponse(code = 200, message = "update testCase")
    @ResponseStatus(OK)
    @PostMapping(
        value = "/tms/test_cases/{id}",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    TestCaseDTO updateTestCase(@PathVariable("id") Long id, @Valid @RequestBody TestCaseDTO testCaseDTO);

    @ApiOperation("Get testCase")
    @ApiResponse(code = 200, message = "get testCase")
    @ResponseStatus(OK)
    @GetMapping(
        value = "/tms/test_cases/{id}",
        produces = APPLICATION_JSON_VALUE)
    TestCaseDTO getTestCase(@PathVariable("id") Long id);

    @ApiOperation("Delete testCase")
    @ApiResponse(code = 200, message = "delete testCase")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(
        value = "/tms/test_cases/{id}")
    void deleteTestCase(@PathVariable("id") Long id);
}
