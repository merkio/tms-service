package io.space.geek.tms.core.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebSecurity
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ResourceServerConfiguration { // extends ResourceServerConfigurerAdapter {

//    private static final String RESOURCE_ID = "tms";
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.resourceId(RESOURCE_ID).stateless(false);
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        //@formatter:off
//        http
//          .cors().disable()
//          .csrf().disable()
//          .authorizeRequests()
//          .antMatchers("/actuator/**").permitAll()
//          .antMatchers("/error").permitAll()
//          .anyRequest().authenticated();
//        //@formatter:on
//    }
}
