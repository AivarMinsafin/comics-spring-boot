package ru.itis.aivar.comics.app.utils.images;


import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.aivar.comics.app.exceptions.NotFoundException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class ImageStorageUtilImpl implements ImageStorageUtil {

    @Value("${dir.titles}")
    private String imagesPath;

    @Override
    public byte[] getImageBytes(String path) {
        try {
            File image = new File(String.valueOf(Paths.get(imagesPath, path)));
            String mimeType = Files.probeContentType(Paths.get(image.getAbsolutePath()));
            BufferedImage bufferedImage = ImageIO.read(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, mimeType.substring("image/".length()), bos);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new NotFoundException(e);
        }
    }

    @Override
    public void deleteDirectoryByPath(String path) {
        try {
            File delete = new File(String.valueOf(Paths.get(imagesPath, path)));
            if (delete.isDirectory()) {
                FileUtils.deleteDirectory(delete);
            }
        } catch (IOException e) {
            throw new ImageStorageUtilException("Unable to delete directory");
        }
    }

    @Override
    public void saveImageMultipartFile(String path, MultipartFile file) {
        File toSave = new File(String.valueOf(Paths.get(imagesPath, path)));
        try {
            if (!toSave.getParentFile().isDirectory() || !toSave.getParentFile().exists()) {
                FileUtils.forceMkdirParent(toSave);
            }
            toSave.createNewFile();
            file.transferTo(toSave);
        } catch (IOException e) {
            throw new ImageStorageUtilException("Unable to save multipart file");
        }
    }

    @Override
    public boolean exists(String path) {
        return Files.exists(Paths.get(imagesPath, path));
    }

    @Override
    public File getDirectoryOfImage(String path) {
        File file = new File(String.valueOf(Paths.get(imagesPath, path)));
        file = file.getParentFile();
        if (file.isDirectory()){
            return file;
        } else {
            throw new ImageStorageUtilException("There is no directory of this image");
        }
    }

    @Override
    public void deleteImage(String path) {
        File image = new File(String.valueOf(Paths.get(imagesPath, path)));
        if (image.exists()){
            try {
                FileUtils.forceDelete(image);
            } catch (IOException e) {
                throw new ImageStorageUtilException("Unable to delete image");
            }
        }
    }

}
