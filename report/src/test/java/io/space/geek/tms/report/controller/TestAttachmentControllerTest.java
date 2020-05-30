package io.space.geek.tms.report.controller;

import io.space.geek.tms.commons.dto.report.TestRunDTO;
import io.space.geek.tms.report.util.FilesUtils;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestAttachmentControllerTest {

    @Value("http://localhost:${local.server.port}/report/attachment/")
    private String baseUrl;

    @Value("${storage.files.rootPath}")
    private String rootPath;

    @BeforeEach
    void init() throws IOException {
        FileUtils.cleanDirectory(Path.of(rootPath).toFile());
    }

    @Test
    void uploadMultiPartFileWithRunId() {
        File file = FilesUtils.loadFile("files/file.txt");
        String runId = "firstRunId";

        //@formatter:off
        String result =
            given()
                .log().all()
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .multiPart(file)
                .baseUri(baseUrl)
                .pathParam("runId", runId)
            .when()
                .post("/{runId}/upload")
                .prettyPeek()
            .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .body().asString();
        //@formatter:on

        assertTrue(FilesUtils.pathExist(Path.of(rootPath, runId, file.getName())));
        assertEquals(String.format("%s/%s", runId, file.getName()), result);
    }
 }
