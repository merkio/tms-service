package io.space.geek.tms.commons.client.report;

import io.space.geek.tms.commons.dto.report.TestRunDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ApiModel(description = "Operations with test run")
@FeignClient(value = "test-run-api", url = "http://localhost:8282")
public interface TestRunApi {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
        value = "/report/test-run",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE
    )
    @ApiOperation("Create test run")
    @ApiResponse(code = 201, message = "Created new test run")
    TestRunDTO createTestRun(@Valid @RequestBody TestRunDTO testRunDTO);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(
        value = {"/report/test-run/{id}"},
        produces = APPLICATION_JSON_VALUE
    )
    @ApiOperation("Get test run by id")
    @ApiResponse(code = 200, message = "Get test run by id")
    TestRunDTO getTestRun(@PathVariable("id") String id);

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(
        value = {"/report/test-run/{id}"},
        produces = APPLICATION_JSON_VALUE,
        consumes = APPLICATION_JSON_VALUE
    )
    @ApiOperation("Update test run with id")
    @ApiResponse(code = 200, message = "Updated test run")
    TestRunDTO updateTestRun(@PathVariable("id") String id, @Valid @RequestBody TestRunDTO testRunDTO);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(
        value = {"/report/test-run/{id}"}
    )
    @ApiOperation("Delete test run with id")
    @ApiResponse(code = 204, message = "")
    void deleteTestRun(@PathVariable("id") String id);

    @GetMapping(
        value = "/report/test-run/all",
        produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all test runs")
    @ApiResponse(code = 200, message = "All test runs")
    List<TestRunDTO> getAll();
}
