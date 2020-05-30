package io.space.geek.tms.core.repository;

import io.space.geek.tms.core.domain.FeatureSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FeatureSpecificationRepository extends JpaRepository<FeatureSpecification, Long>,
    JpaSpecificationExecutor<FeatureSpecification> {
}
