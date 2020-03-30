package com.company.tms.auth.repository;

import com.company.tms.auth.document.AuthorizationCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizationCodeRepository extends MongoRepository<AuthorizationCode, String> {

    Optional<AuthorizationCode> findByCode(String code);
}
