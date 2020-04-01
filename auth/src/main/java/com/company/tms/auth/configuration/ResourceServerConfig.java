package com.company.tms.auth.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "auth";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
            .cors().disable()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/actuator/**").permitAll()
            .antMatchers("/oauth/**").permitAll()
            .antMatchers(HttpMethod.POST, "/api/v1/auth/user").permitAll()
            .antMatchers(HttpMethod.PUT, "/api/v1/auth/user/*").hasAnyRole("USER")
            .antMatchers(HttpMethod.GET, "/api/v1/auth/user/*").hasAnyRole("USER")
            .antMatchers("/api/v1/auth/users").hasAnyRole("ADMIN")
            .antMatchers("/api/v1/auth/user/**").hasAnyRole("ADMIN")
            .antMatchers("/api/v1/auth/client_details/**").hasRole("ADMIN")
            .anyRequest().authenticated();
        //@formatter:on
    }

}
