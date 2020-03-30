package com.company.tms.auth.service;

import com.company.tms.auth.document.AuthorizationCode;
import com.company.tms.auth.exception.AuthorizationCodeNotFoundException;
import com.company.tms.auth.repository.AuthorizationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

    private final AuthorizationCodeRepository authorizationCodeRepository;

    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        AuthorizationCode authorizationCode = new AuthorizationCode();
        authorizationCode.setCode(code);
        authorizationCode.setAuthentication(authentication);

        authorizationCodeRepository.save(authorizationCode);
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        AuthorizationCode authorizationCode = authorizationCodeRepository.findByCode(code)
            .orElseThrow(() -> new AuthorizationCodeNotFoundException("OAuth2Authentication wit code [{}] not found", code));

        return authorizationCode.getAuthentication();
    }
}