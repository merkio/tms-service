package io.space.geek.tms.report.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public final class FilesUtils {


    @SneakyThrows(IOException.class)
    public static void createFolder(String path) {
        log.info("Create folder [{}]", path);
        Files.createDirectories(Path.of(path));
    }

    @SneakyThrows(IOException.class)
    public static void createFolder(Path path) {
        log.info("Create folder [{}]", path);
        Files.createDirectories(path);
    }

    public static boolean pathExist(String path) {
        log.info("Check if path [{}] is exist", path);
        return Files.exists(Path.of(path));
    }

    public static boolean pathExist(Path path) {
        log.info("Check if path [{}] is exist", path);
        return Files.exists(path);
    }

    @SneakyThrows(IOException.class)
    public static void save(MultipartFile file, String filePath, String rootPath) {
        log.info("Save file [{}] to the directory [{}/{}]", file.getOriginalFilename(), rootPath, filePath);
        createFolder(Path.of(rootPath, filePath));
        FileUtils.writeByteArrayToFile(Path.of(rootPath, filePath, file.getOriginalFilename()).toFile(), file.getBytes());
    }

    @SneakyThrows(IOException.class)
    public static void save(File file, String fileName, String filePath, String rootPath) {
        log.info("Save file [{}] to the directory [{}/{}]", file.getName(), rootPath, filePath);
        createFolder(Path.of(rootPath, filePath));
        FileUtils.copyFile(file, Path.of(rootPath, filePath, fileName).toFile());
    }

    public static File loadFile(String name) {
        return new File(getResource(name).getFile());
    }

    private static URL getResource(String name) {
        return FileUtils.class.getClassLoader().getResource(name);
    }
}
