package com.company.tms.auth.service;

import com.company.tms.auth.document.AuthAccessToken;
import com.company.tms.auth.document.RefreshToken;
import com.company.tms.auth.repository.AuthAccessTokenRepository;
import com.company.tms.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthTokenStore implements TokenStore {

    private final AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    private final AuthAccessTokenRepository accessTokenRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken accessToken) {
        return readAuthentication(accessToken.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
        Optional<AuthAccessToken> accessTokenO = accessTokenRepository.findByTokenId(token);
        return accessTokenO.map(AuthAccessToken::getAuthentication).orElse(null);
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        String refreshToken = null;
        if (Objects.nonNull(accessToken.getRefreshToken())) {
            refreshToken = accessToken.getRefreshToken().getValue();
        }

        if (Objects.nonNull(readAccessToken(accessToken.getValue()))) {
            this.removeAccessToken(accessToken);
        }

        AuthAccessToken authAccessToken = new AuthAccessToken();
        authAccessToken.setTokenId(extractTokenKey(accessToken.getValue()));
        authAccessToken.setToken(accessToken);
        authAccessToken.setAuthenticationId(authenticationKeyGenerator.extractKey(authentication));
        authAccessToken.setUsername(authentication.isClientOnly() ? null : authentication.getName());
        authAccessToken.setClientId(authentication.getOAuth2Request().getClientId());
        authAccessToken.setAuthentication(authentication);
        authAccessToken.setRefreshToken(extractTokenKey(refreshToken));

        accessTokenRepository.save(authAccessToken);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        Optional<AuthAccessToken> accessTokenO = accessTokenRepository.findByTokenId(tokenValue);
        return accessTokenO.map(AuthAccessToken::getToken).orElse(null);
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken oAuth2AccessToken) {
        accessTokenRepository.removeByTokenId(oAuth2AccessToken.getValue());
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        RefreshToken token = new RefreshToken();
        token.setTokenId(extractTokenKey(refreshToken.getValue()));
        token.setToken(refreshToken);
        token.setAuthentication(authentication);
        refreshTokenRepository.save(token);
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        Optional<RefreshToken> refreshTokenO = refreshTokenRepository.findByTokenId(tokenValue);
        return refreshTokenO.map(RefreshToken::getToken).orElse(null);
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken refreshToken) {
        Optional<RefreshToken> refreshTokenO = refreshTokenRepository.findByTokenId(refreshToken.getValue());
        return refreshTokenO.map(RefreshToken::getAuthentication).orElse(null);
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken refreshToken) {
        refreshTokenRepository.removeByTokenId(refreshToken.getValue());
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        accessTokenRepository.removeByRefreshToken(extractTokenKey(refreshToken.getValue()));
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        OAuth2AccessToken accessToken = null;
        String authenticationId = authenticationKeyGenerator.extractKey(authentication);

        Optional<AuthAccessToken> authAccessTokenO = accessTokenRepository.findByAuthenticationId(authenticationId);
        if (authAccessTokenO.isPresent()) {
            accessToken = authAccessTokenO.get().getToken();
            if (Objects.nonNull(accessToken) && !authenticationId.equals(this.authenticationKeyGenerator.extractKey(this.readAuthentication(accessToken)))) {
                this.removeAccessToken(accessToken);
                this.storeAccessToken(accessToken, authentication);
            }
        }
        return accessToken;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String username) {
        return accessTokenRepository.findByClientIdAndUsername(clientId, username).stream()
            .map(AuthAccessToken::getToken)
            .collect(Collectors.toList());
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        return accessTokenRepository.findByClientId(clientId).stream()
            .map(AuthAccessToken::getToken)
            .collect(Collectors.toList());
    }

    private String extractTokenKey(String value) {
        if (Objects.isNull(value)) {
            return null;
        }
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var5) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
        }

        byte[] e = digest.digest(value.getBytes(StandardCharsets.UTF_8));
        return String.format("%032x", new BigInteger(1, e));
    }
}