package io.space.geek.tms.report.service;

import org.springframework.web.multipart.MultipartFile;

public interface ITestAttachmentService {

    String uploadAttachment(MultipartFile file, String runId);

    String uploadAttachment(MultipartFile file, String folderName, String fileName);

    void deleteAttachment(String runId, String name);
}
