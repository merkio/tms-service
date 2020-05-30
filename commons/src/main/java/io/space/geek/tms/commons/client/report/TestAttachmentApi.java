package io.space.geek.tms.commons.client.report;

import io.space.geek.tms.commons.dto.report.TestAttachmentDTO;
import io.swagger.annotations.ApiModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@ApiModel(description = "Operations with test attachments")
@FeignClient(value = "test-attachments-api", url = "http://localhost:8282")
public interface TestAttachmentApi {

    @ResponseStatus(CREATED)
    @PostMapping(
        value = "/report/attachment/{runId}/upload",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadAttachment(@RequestPart(value = "file") MultipartFile file,
                                       @PathVariable("runId") String runId);

    @ResponseStatus(CREATED)
    @PostMapping(
        value = "/report/attachment/upload/{folderName}/{fileName}",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadAttachment(@RequestPart(value = "file") MultipartFile file,
                                       @PathVariable("folderName") String folderName,
                                       @PathVariable("fileName") String fileName);

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(
        value = "/report/attachment/{runId}/{fileName}"
    )
    void deleteAttachment(@PathVariable("runId") String runId, @PathVariable("fileName") String fileName);
}
