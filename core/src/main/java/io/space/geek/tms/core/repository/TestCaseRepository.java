package io.space.geek.tms.core.repository;

import io.space.geek.tms.core.domain.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long>, JpaSpecificationExecutor<TestCase> {

    List<TestCase> findByAcceptanceCriteriaId(@NotNull Long acceptanceCriteriaId);

    Integer countByFeatureId(Long featureId);

    Long countByFeatureIdAndAutomated(Long featureId, Boolean automated);
}
