package io.space.geek.tms.core.controller;

import io.space.geek.tms.commons.dto.tms.FeatureDTO;
import io.space.geek.tms.core.domain.Feature;
import io.space.geek.tms.core.repository.FeatureRepository;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureEmbeddedDatabase(provider = DOCKER)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FeatureControllerTest {

    @Value("http://localhost:${local.server.port}/tms/features")
    private String baseUrl;

    @Autowired
    private FeatureRepository featureRepository;

    @BeforeEach
    void init() {
        featureRepository.deleteAll();
    }

    @Test
    void getFeature() {
        Feature feature = Feature.builder()
            .name("Feature")
            .businessArea("Area")
            .businessDomain("Domain")
            .build();

        Feature saveFeature = featureRepository.saveAndFlush(feature);
        assertNotNull(saveFeature.getId());
        assertEquals(feature.getName(), saveFeature.getName());

        //@formatter:off
        FeatureDTO result =
            given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .when()
                .get(baseUrl + "/" + saveFeature.getId())
                .prettyPeek()
            .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .response().as(FeatureDTO.class);
        //@formatter:on

        assertEquals(saveFeature.getId(), result.getId());
        assertEquals(saveFeature.getName(), result.getName());
    }
}
