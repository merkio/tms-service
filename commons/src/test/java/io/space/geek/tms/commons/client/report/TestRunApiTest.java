package io.space.geek.tms.commons.client.report;

import feign.Feign;
import feign.Logger;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestRunApiTest {

    @Test
    void createClient() {
        TestRunApi runApi = Feign.builder()
            .client(new OkHttpClient())
            .logger(new Slf4jLogger())
            .logLevel(Logger.Level.FULL)
            .target(TestRunApi.class, "http://localhost");

        assertNotNull(runApi);
    }

}