package io.space.geek.tms.commons.client.tms;

import io.space.geek.tms.commons.dto.tms.TestSuiteDTO;
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

@FeignClient(value = "test-suites-api", url = "http://localhost:8181")
@ApiModel(description = "Operations with test-suites")
public interface TestSuiteApi {
    
    @ApiOperation("Get all test_suites")
    @ApiResponse(code = 200, message = "get all test_suites")
    @ResponseStatus(OK)
    @GetMapping(
        value = "/tms/test_suites",
        produces = APPLICATION_JSON_VALUE)
    List<TestSuiteDTO> getTestSuites();

    @ApiOperation("Add a new testSuite")
    @ApiResponse(code = 200, message = "add a new testSuite")
    @ResponseStatus(OK)
    @PostMapping(
        value = "/tms/test_suites",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    TestSuiteDTO addTestSuite(@Valid @RequestBody TestSuiteDTO testSuiteDTO);

    @ApiOperation("Update testSuite")
    @ApiResponse(code = 200, message = "update testSuite")
    @ResponseStatus(OK)
    @PostMapping(
        value = "/tms/test_suites/{id}",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    TestSuiteDTO updateTestSuite(@PathVariable("id") Long id, @Valid @RequestBody TestSuiteDTO testSuiteDTO);

    @ApiOperation("Get testSuite")
    @ApiResponse(code = 200, message = "get testSuite")
    @ResponseStatus(OK)
    @GetMapping(
        value = "/tms/test_suites/{id}",
        produces = APPLICATION_JSON_VALUE)
    TestSuiteDTO getTestSuite(@PathVariable("id") Long id);

    @ApiOperation("Delete testSuite")
    @ApiResponse(code = 200, message = "delete testSuite")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(
        value = "/tms/test_suites/{id}")
    void deleteTestSuite(@PathVariable("id") Long id);
}
