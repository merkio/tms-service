package com.company.tms.auth.configuration;

import com.company.tms.auth.service.AuthAuthorizationCodeServices;
import com.company.tms.auth.service.AuthClientDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final TokenStore tokenStore;
    private final MongoTemplate mongoTemplate;
    private final AuthClientDetailsService authClientDetailsService;

    @Bean
    public AuthAuthorizationCodeServices authorizationCodeServices() {
        return new AuthAuthorizationCodeServices(mongoTemplate);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(authClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authorizationCodeServices(authorizationCodeServices())
                .tokenServices(tokenServices())
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()");
    }

    @Primary
    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore);

        List<TokenEnhancer> enhancers = new ArrayList<>();

        //Some custom enhancer
        enhancers.add((accessToken, authentication) -> {
            final Authentication userAuthentication = authentication.getUserAuthentication();

            final DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
            Set<String> existingScopes = new HashSet<>(defaultOAuth2AccessToken.getScope());
            if (userAuthentication != null) {
                //User has logged into system
                existingScopes.add("read");
                existingScopes.add("write");
            } else {
                //service is trying to access system
                existingScopes.add("another-scope");
            }

            defaultOAuth2AccessToken.setScope(existingScopes);
            return defaultOAuth2AccessToken;
        });

        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(enhancers);
        tokenServices.setTokenEnhancer(enhancerChain);

        return tokenServices;
    }
}
