package com.company.tms.auth.repository;

import com.company.tms.auth.document.AuthClientDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthClientDetailsRepository extends MongoRepository<AuthClientDetails, String> {

    Optional<AuthClientDetails> findByClientId(String clientId);
}
