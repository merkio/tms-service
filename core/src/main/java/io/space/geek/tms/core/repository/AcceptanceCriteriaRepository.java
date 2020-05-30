package io.space.geek.tms.core.repository;

import io.space.geek.tms.core.domain.AcceptanceCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcceptanceCriteriaRepository extends JpaRepository<AcceptanceCriteria, Long>, JpaSpecificationExecutor<AcceptanceCriteria> {

    List<AcceptanceCriteria> findAllByFeatureId(Long featureId);
}
