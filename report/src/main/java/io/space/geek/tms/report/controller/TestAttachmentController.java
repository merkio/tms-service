package io.space.geek.tms.report.controller;

import io.space.geek.tms.commons.client.report.TestAttachmentApi;
import io.space.geek.tms.report.service.ITestAttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@RequiredArgsConstructor
public class TestAttachmentController implements TestAttachmentApi {

    private final ITestAttachmentService attachmentService;

    @Override
    public String uploadAttachment(@RequestPart(value = "file") MultipartFile file,
                                   @PathVariable("runId") String runId) {
        return attachmentService.uploadAttachment(file, runId);
    }

    @Override
    public String uploadAttachment(@RequestPart(value = "file") MultipartFile file,
                                   @PathVariable("folderName") String folderName,
                                   @PathVariable("fileName") String fileName) {
        return attachmentService.uploadAttachment(file, folderName, fileName);
    }

    @Override
    public void deleteAttachment(@PathVariable("runId") String runId,
                                 @PathVariable("fileName") String fileName) {
        attachmentService.deleteAttachment(runId, fileName);
    }
}
