package io.space.geek.tms.commons.client.report;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.space.geek.tms.commons.dto.report.TestRunDTO;
import io.swagger.annotations.ApiModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ApiModel(description = "Operations with test run")
public interface TestRunApi {

    @ResponseStatus(HttpStatus.CREATED)
    @RequestLine("POST /report/test-run")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    TestRunDTO createTestRun(TestRunDTO testRunDTO);

    @ResponseStatus(HttpStatus.OK)
    @RequestLine("GET /report/test-run/{id}")
    @Headers({"Content-Type: application/json"})
    TestRunDTO getTestRun(@Param("id") String id);

    @ResponseStatus(HttpStatus.OK)
    @RequestLine("PUT /report/test-run/{id}")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    TestRunDTO updateTestRun(@Param("id") String id, TestRunDTO testRunDTO);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestLine("DELETE /report/test-run/{id}")
    void deleteTestRun(@Param("id") String id);

    @ResponseStatus(HttpStatus.OK)
    @RequestLine("GET /report/test-run/all")
    @Headers({"Content-Type: application/json"})
    List<TestRunDTO> getAll();
}
