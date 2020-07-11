package io.space.geek.tms.report.controller;

import io.space.geek.tms.commons.client.report.TestAttachmentApi;
import io.space.geek.tms.report.service.ITestAttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping({"/api/report/attachment"})
public class TestAttachmentController implements TestAttachmentApi {

    private final ITestAttachmentService attachmentService;

    @Override
    @PostMapping("/{runId}/upload")
    public String uploadAttachment(@RequestPart(value = "file") MultipartFile file,
                                   @PathVariable("runId") String runId) {
        return attachmentService.uploadAttachment(file, runId);
    }

    @Override
    @PostMapping("/{runId}/{fileName}")
    public void deleteAttachment(@PathVariable("runId") String runId,
                                 @PathVariable("fileName") String fileName) {
        attachmentService.deleteAttachment(runId, fileName);
    }
}
