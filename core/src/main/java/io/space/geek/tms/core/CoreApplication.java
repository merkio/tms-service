package io.space.geek.tms.core;

import io.space.geek.tms.commons.config.FeignClientsConfiguration;
import io.space.geek.tms.commons.config.ModelMapperConfiguration;
import io.space.geek.tms.commons.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties
@Import({
    ModelMapperConfiguration.class,
    FeignClientsConfiguration.class,
    SwaggerConfiguration.class
})
public class CoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }
}
