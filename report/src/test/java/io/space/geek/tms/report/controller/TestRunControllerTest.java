package io.space.geek.tms.report.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.space.geek.tms.commons.dto.report.TestRunConfigurationDTO;
import io.space.geek.tms.commons.dto.report.TestRunDTO;
import io.space.geek.tms.report.domain.TestRun;
import io.space.geek.tms.report.repository.TestRunRepository;
import io.space.geek.tms.report.service.ITestRunService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRunControllerTest {

    @Value("http://localhost:${local.server.port}/report/test-run")
    private String baseUrl;

    @Autowired
    private TestRunRepository testRunRepository;

    @Autowired
    private ITestRunService ITestRunService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void init() {
        testRunRepository.deleteAll();
    }

    @Test
    void createTestRun() throws JsonProcessingException {
        TestRunDTO testRunDTO = TestRunDTO.builder()
            .name("TestRun")
            .projectId(1L)
            .featureId(2L)
            .description("Description")
            .configuration(TestRunConfigurationDTO.builder().environment("qa").platform("").build())
            .results(Collections.emptySet())
            .build();

        //@formatter:off
        TestRunDTO result =
            given()
                .log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(testRunDTO)
            .when()
                .post(baseUrl)
                .prettyPeek()
            .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .body().as(TestRunDTO.class);
        //@formatter:on

        assertNotNull(result.getId());
        assertEquals(testRunDTO.getName(), result.getName());
        assertEquals(testRunDTO.getConfiguration().getEnvironment(), result.getConfiguration().getEnvironment());
    }

    @Test
    void getTestRun() {
        TestRun testRun = TestRun.builder()
            .name("TestRun")
            .description("Description")
            .configuration(TestRunConfigurationDTO.builder().environment("qa").platform("").build())
            .results(Collections.emptySet())
            .build();

        testRunRepository.save(testRun);

        assertTrue(testRunRepository.findById(testRun.getId()).isPresent());

        //@formatter:off
        TestRunDTO result =
            given()
                .log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .get(baseUrl + "/" + testRun.getId())
                .prettyPeek()
            .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body().as(TestRunDTO.class);
        //@formatter:on

        assertNotNull(result.getId());
        assertEquals(testRun.getName(), result.getName());
        assertEquals(testRun.getConfiguration().getEnvironment(), result.getConfiguration().getEnvironment());
    }

    @Test
    void getAll() {
        TestRun testRun = TestRun.builder()
            .name("TestRun")
            .description("Description")
            .configuration(TestRunConfigurationDTO.builder().environment("qa").platform("").build())
            .results(Collections.emptySet())
            .build();

        testRunRepository.save(testRun);

        assertTrue(testRunRepository.findById(testRun.getId()).isPresent());

        //@formatter:off
        String response =
            given()
                .log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
            .get(baseUrl + "/" + testRun.getId())
                .prettyPeek()
            .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body().asString();
        //@formatter:on

        assertFalse(response.isBlank());
    }
}
