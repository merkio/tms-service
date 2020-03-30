package com.company.tms.auth.repository;

import com.company.tms.auth.document.AuthAccessToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthAccessTokenRepository extends MongoRepository<AuthAccessToken, String> {

    Optional<AuthAccessToken> findByTokenId(String tokenId);

    List<AuthAccessToken> findByClientId(String clientId);

    Optional<AuthAccessToken> findByAuthenticationId(String authenticationId);

    List<AuthAccessToken> findByClientIdAndUsername(String clientId, String username);

    void removeByTokenId(String tokenId);

    void removeByRefreshToken(String refreshToken);
}
