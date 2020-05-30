package io.space.geek.tms.core.controller;


import io.space.geek.tms.commons.dto.tms.AcceptanceCriteriaDTO;
import io.space.geek.tms.core.domain.AcceptanceCriteria;
import io.space.geek.tms.core.domain.Feature;
import io.space.geek.tms.core.repository.AcceptanceCriteriaRepository;
import io.space.geek.tms.core.repository.FeatureRepository;
import io.space.geek.tms.core.service.AcceptanceCriteriaService;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.DOCKER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureEmbeddedDatabase(provider = DOCKER)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceCriteriaControllerTest {

    @Value("http://localhost:${local.server.port}/tms/acceptance_criteria")
    private String baseUrl;

    @Autowired
    private AcceptanceCriteriaRepository criteriaRepository;

    @Autowired
    private AcceptanceCriteriaService criteriaService;

    @Autowired
    private FeatureRepository featureRepository;

    @BeforeEach
    void init() {
        criteriaRepository.deleteAll();
        featureRepository.deleteAll();
    }

    @Test
    void getAcceptanceCriteria() {
        Feature savedFeature = featureRepository.save(
            Feature.builder()
                .name("Feature")
                .automationApplicationMap(true)
                .build());

        AcceptanceCriteria savedAC = criteriaRepository.saveAndFlush(
            AcceptanceCriteria.builder()
                .name("AC1")
                .featureId(savedFeature.getId())
                .description("AC for feature")
                .build());

        assertTrue(criteriaRepository.findById(savedAC.getId()).isPresent());
        assertEquals(savedAC.getName(), criteriaService.getAcceptanceCriteria(savedAC.getId()).getName());

        //@formatter:off
        AcceptanceCriteriaDTO result =
            given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .when()
                .get(baseUrl + "/" + savedAC.getId())
                .prettyPeek()
            .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .response().as(AcceptanceCriteriaDTO.class);
        //@formatter:on

        assertEquals(savedAC.getId(), result.getId());
        assertEquals(savedAC.getName(), result.getName());
    }
}
