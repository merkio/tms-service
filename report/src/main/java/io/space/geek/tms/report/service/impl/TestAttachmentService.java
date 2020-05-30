package io.space.geek.tms.report.service.impl;

import io.space.geek.tms.report.service.IFileStorageService;
import io.space.geek.tms.report.service.ITestAttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestAttachmentService implements ITestAttachmentService {

    private final IFileStorageService fileService;

    @Override
    public String uploadAttachment(MultipartFile file, String runId) {
        return fileService.uploadFileToFolder(file, runId);
    }

    @Override
    public String uploadAttachment(MultipartFile file, String folderName, String fileName) {
        return fileService.uploadFileToFolder(file, folderName, fileName);
    }

    @Override
    public void deleteAttachment(String runId, String name) {
        fileService.deleteObject(runId, name);
    }
}
