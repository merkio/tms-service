package io.space.geek.tms.report.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {

    String uploadFile(MultipartFile file);

    String uploadFileToFolder(MultipartFile file, String folderName);

    String uploadFileToFolder(MultipartFile file, String folderName, String fileName);

    String uploadStringToFile(String content, String folderName, String fileName);

    void deleteObject(String path);

    void deleteObject(String folderName, String fileName);
}
