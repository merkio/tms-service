package io.space.geek.tms.core.repository;

import io.space.geek.tms.core.domain.AcceptanceCriteria;
import io.space.geek.tms.core.domain.Feature;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.DOCKER;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@AutoConfigureEmbeddedDatabase(provider = DOCKER)
public class AcceptanceCriteriaRepositoryTest {

    @Autowired
    private AcceptanceCriteriaRepository criteriaRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Test
    void saveAcceptanceCriteria() {
        Feature feature = featureRepository.saveAndFlush(Feature.builder().name("Feature").build());

        AcceptanceCriteria ac = AcceptanceCriteria.builder()
            .name("First")
            .featureId(feature.getId())
            .orderId(0)
            .build();

        AcceptanceCriteria savedAC = criteriaRepository.saveAndFlush(ac);

        assertNotNull(savedAC.getId());
        assertEquals(ac.getFeatureId(), savedAC.getFeatureId());
        assertEquals(ac.getName(), savedAC.getName());
    }

    @Test
    void saveAndFindAcceptanceCriteria() {
        Feature feature = featureRepository.saveAndFlush(Feature.builder().name("Feature").build());

        AcceptanceCriteria ac = AcceptanceCriteria.builder()
            .name("First")
            .featureId(feature.getId())
            .orderId(0)
            .build();

        AcceptanceCriteria savedAC = criteriaRepository.saveAndFlush(ac);

        Optional<AcceptanceCriteria> findACO = criteriaRepository.findById(savedAC.getId());
        assertTrue(findACO.isPresent());
        AcceptanceCriteria findAC = findACO.get();

        assertNotNull(findAC.getId());
        assertEquals(ac.getFeatureId(), findAC.getFeatureId());
        assertEquals(ac.getName(), findAC.getName());
    }
}
