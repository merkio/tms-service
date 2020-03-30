package com.company.tms.auth;

import com.company.tms.auth.document.*;
import com.company.tms.commons.config.ModelMapperConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@EnableFeignClients({"com.company.tms.commons"})
@Import({ModelMapperConfiguration.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AuthApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(AuthApplication.class, args);

        MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);

        mongoTemplate.dropCollection(User.class);
        mongoTemplate.dropCollection(AuthClientDetails.class);
        mongoTemplate.dropCollection(AuthAccessToken.class);
        mongoTemplate.dropCollection(AuthorizationCode.class);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // init the users
        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setRoles(Set.of((UserRole.ROLE_USER.name())));
        mongoTemplate.save(user);

        // init the client details
        AuthClientDetails clientDetails = new AuthClientDetails();
        clientDetails.setClientId("tms");
        clientDetails.setClientSecret(passwordEncoder.encode("tms-secret"));
        clientDetails.setSecretRequired(true);
        clientDetails.setResourceIds(Set.of("tms"));
        clientDetails.setScope(Set.of("read", "write"));
        clientDetails.setAuthorizedGrantTypes(Set.of("authorization_code", "refresh_token",
                                                     "password", "client_credentials"));
        clientDetails.setRegisteredRedirectUri(Set.of("http://localhost:8082/api/v1/users"));
        clientDetails.setAuthorities(AuthorityUtils.createAuthorityList(UserRole.ROLE_USER.name()));
        clientDetails.setAccessTokenValiditySeconds(60);
        clientDetails.setRefreshTokenValiditySeconds(14400);
        clientDetails.setAutoApprove(false);
        mongoTemplate.save(clientDetails);
    }
}
