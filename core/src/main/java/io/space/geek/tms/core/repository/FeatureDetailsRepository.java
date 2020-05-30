package io.space.geek.tms.core.repository;

import io.space.geek.tms.core.domain.FeatureDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface FeatureDetailsRepository extends JpaRepository<FeatureDetails, Long>, JpaSpecificationExecutor<FeatureDetails> {

    Optional<FeatureDetails> findByFeatureId(Long id);
}
