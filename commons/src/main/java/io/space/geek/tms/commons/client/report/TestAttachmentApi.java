package io.space.geek.tms.commons.client.report;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.swagger.annotations.ApiModel;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@ApiModel(description = "Operations with test attachments")
public interface TestAttachmentApi {

    @ResponseStatus(CREATED)
    @RequestLine("POST /report/attachment/{runId}/upload")
    @Headers("Content-Type: multipart/form-data")
    String uploadAttachment(@Param(value = "file") MultipartFile file,
                            @Param("runId") String runId);

    @ResponseStatus(NO_CONTENT)
    @RequestLine("DELETE /report/attachment/{runId}/{fileName}")
    void deleteAttachment(@Param("runId") String runId, @Param("fileName") String fileName);
}
