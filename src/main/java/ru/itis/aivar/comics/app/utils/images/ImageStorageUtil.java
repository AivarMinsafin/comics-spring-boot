package ru.itis.aivar.comics.app.utils.images;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ImageStorageUtil {

    byte[] getImageBytes(String path);

    void deleteDirectoryByPath(String path);

    void saveImageMultipartFile(String path, MultipartFile file);

    boolean exists(String path);

    File getDirectoryOfImage(String path);

    void deleteImage(String path);
}
