package io.space.geek.tms.report.service.impl;

import io.space.geek.tms.report.service.IFileStorageService;
import io.space.geek.tms.report.util.FilesUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

@Slf4j
@Service
public class FileStorageService implements IFileStorageService {

    private final String rootPath;

    public FileStorageService(@Value("${storage.files.rootPath}") String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        return uploadFileToFolder(file, null, file.getOriginalFilename());
    }

    @Override
    public String uploadFileToFolder(MultipartFile file, String folderName) {
        return uploadFileToFolder(file, folderName, file.getOriginalFilename());
    }

    @Override
    public String uploadFileToFolder(MultipartFile file, String folderName, String fileName) {
        String filePath = StringUtils.isNotBlank(folderName) ? folderName : StringUtils.EMPTY;
        log.info("Uploading file [{}]", filePath);
        FilesUtils.save(file, filePath, rootPath);
        log.info("Successfully uploaded file [{}/{}]", filePath, fileName);
        return String.format("%s/%s", folderName, fileName);
    }

    @Override
    @SneakyThrows({IOException.class})
    public String uploadStringToFile(String content, String folderName, String fileName) {
        log.info("Uploading string to file [{}/{}]", folderName, fileName);
        File file = File.createTempFile(fileName, ".tmp");
        FileUtils.writeStringToFile(file, content, StandardCharsets.UTF_8);
        FilesUtils.save(file, fileName, folderName, rootPath);
        log.info("Successfully uploaded file [{}/{}]", folderName, fileName);
        return String.format("%s/%s", folderName, fileName);
    }

    @Override
    public void deleteObject(String folderName, String fileName) {
        deleteObject(StringUtils.isNotBlank(folderName) ? String.format("%s/%s", folderName, fileName) : fileName);
    }

    @Override
    public void deleteObject(String path) {
        log.info("Deleting file [{}/{}]", rootPath, path);
        FileUtils.deleteQuietly(Path.of(rootPath, path).toFile());
    }
}
