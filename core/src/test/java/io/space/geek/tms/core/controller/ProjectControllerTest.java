package io.space.geek.tms.core.controller;


import io.space.geek.tms.commons.constant.tms.ProjectType;
import io.space.geek.tms.commons.dto.tms.ProjectDTO;
import io.space.geek.tms.core.CoreApplication;
import io.space.geek.tms.core.domain.Project;
import io.space.geek.tms.core.repository.ProjectRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.DOCKER;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureEmbeddedDatabase(provider = DOCKER)
@ContextConfiguration(classes = CoreApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerTest {

    @Value("http://localhost:${local.server.port}/tms/projects")
    private String baseUrl;

    @Autowired
    private ProjectRepository projectRepository;

    @BeforeEach
    void init() {
        projectRepository.deleteAll();
    }

    @Test
    void getProject() {
        Project project = Project.builder()
            .name("Project")
            .type(ProjectType.APPLICATION)
            .description("Some description")
            .imagePath("image")
            .build();

        Project savedProject = projectRepository.saveAndFlush(project);

        Optional<Project> currentProjectO = projectRepository.findById(savedProject.getId());
        assertTrue(currentProjectO.isPresent());

        //@formatter:off
        ProjectDTO result =
            given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .when()
                .get(baseUrl + "/" + savedProject.getId())
                .prettyPeek()
            .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .response()
                .as(ProjectDTO.class);
        //@formatter:on

        assertNotNull(result.getId());
        assertEquals(project.getName(), result.getName());
        assertEquals(project.getDescription(), result.getDescription());
    }
}
