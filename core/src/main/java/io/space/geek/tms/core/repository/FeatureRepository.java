package io.space.geek.tms.core.repository;

import io.space.geek.tms.core.domain.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long>, JpaSpecificationExecutor<Feature> {

    List<Feature> findByProjectId(Long projectId);

    List<Feature> findAllByParentId(Long featureId);
}
