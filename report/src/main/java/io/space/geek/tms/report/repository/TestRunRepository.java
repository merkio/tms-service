package io.space.geek.tms.report.repository;

import io.space.geek.tms.report.domain.TestRun;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRunRepository extends MongoRepository<TestRun, String> {

}
