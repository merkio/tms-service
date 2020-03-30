package com.company.tms.auth.repository;

import com.company.tms.auth.document.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {

    Optional<RefreshToken> findByTokenId(String tokenId);

    void removeByTokenId(String tokenId);
}
