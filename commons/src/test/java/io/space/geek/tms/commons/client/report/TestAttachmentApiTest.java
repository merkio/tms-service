package io.space.geek.tms.commons.client.report;

import feign.Feign;
import feign.Logger;
import feign.form.FormEncoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestAttachmentApiTest {

    @Test
    void createClient() {
        TestAttachmentApi attachmentApi = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new FormEncoder(new GsonEncoder()))
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger())
            .logLevel(Logger.Level.FULL)
            .target(TestAttachmentApi.class, "http://localhost");

        assertNotNull(attachmentApi);
    }

}