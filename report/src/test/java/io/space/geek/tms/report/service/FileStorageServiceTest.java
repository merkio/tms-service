package io.space.geek.tms.report.service;

import io.space.geek.tms.report.service.impl.FileStorageService;
import io.space.geek.tms.report.util.FilesUtils;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FileStorageServiceTest {

    @Autowired
    private FileStorageService fileStorageService;

    @Value("${storage.files.rootPath}")
    private String rootPath;

    @BeforeEach
    void init() throws IOException {
        FileUtils.cleanDirectory(Path.of(rootPath).toFile());
    }

    @Test
    void uploadMultiPartFile() {
        MockMultipartFile file = new MockMultipartFile("file", "fileName", "", "Content of new temp file".getBytes());
        String runId = "runId";

        fileStorageService.uploadFileToFolder(file, runId);

        String filePath = Path.of(rootPath, runId, file.getOriginalFilename()).toString();
        assertTrue(FilesUtils.pathExist(filePath));
    }

    @Test
    void uploadStringToFile() {
        String runId = "runId";

        String path = fileStorageService.uploadStringToFile("Data", runId, "newFileName");

        assertEquals(Path.of(runId, "newFileName").toString(), path);
        String filePath = Path.of(rootPath, runId, "newFileName").toString();
        assertTrue(FilesUtils.pathExist(filePath));
    }
}
