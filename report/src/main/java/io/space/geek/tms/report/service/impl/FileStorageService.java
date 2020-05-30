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

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    public String uploadFile(@NotNull MultipartFile file) {
        return uploadFileToFolder(file, "", file.getOriginalFilename());
    }

    @Override
    public String uploadFileToFolder(@NotNull MultipartFile file, @NotNull String folderName) {
        return uploadFileToFolder(file, folderName, file.getOriginalFilename());
    }

    @Override
    public String uploadFileToFolder(@NotNull MultipartFile file, @NotNull String folderName, @NotNull String fileName) {
        String filePath = StringUtils.isNotBlank(folderName) ? folderName : StringUtils.EMPTY;
        log.info("Uploading file [{}]", filePath);
        FilesUtils.move(file, filePath, rootPath);
        return String.format("%s/%s", folderName, fileName);
    }

    @Override
    @SneakyThrows({IOException.class})
    public String uploadStringToFile(@NotNull String content, @NotNull String folderName, @NotNull String fileName) {
        log.info("Save string to file [{}/{}]", folderName, fileName);
        File file = Path.of(rootPath, folderName, fileName).toFile();
        FileUtils.writeStringToFile(file, content, StandardCharsets.UTF_8);
        return String.format("%s/%s", folderName, fileName);
    }

    @Override
    public void deleteObject(@NotEmpty String folderName, @NotEmpty String fileName) {
        deleteObject(String.format("%s/%s", folderName, fileName));
    }

    @Override
    public void deleteObject(@NotEmpty String path) {
        log.info("Deleting file [{}/{}]", rootPath, path);
        FileUtils.deleteQuietly(Path.of(rootPath, path).toFile());
    }
}
