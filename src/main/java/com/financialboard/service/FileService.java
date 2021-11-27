package com.financialboard.service;


import com.financialboard.exception.user.IllegalMineTypeException;
import com.financialboard.exception.post.ImageRoadFailedException;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
public class FileService {

    public static void checkImageMimeType(String mimeType) {
        if (!(mimeType.equals("image/jpg") || mimeType.equals("image/jpeg")
                || mimeType.equals("image/png") || mimeType.equals("image/gif"))) {
            throw new IllegalMineTypeException();
        }
    }

    public static String fileNameConvert(String fileName) {
        StringBuilder builder = new StringBuilder();
        UUID uuid = UUID.randomUUID();
        String extension = getExtension(fileName);

        builder.append(uuid).append(".").append(extension);

        return builder.toString();
    }

    private static String getExtension(String fileName) {
        int pos = fileName.lastIndexOf(".");

        return fileName.substring(pos + 1);
    }

    public static String getFileName(String path) {
        int idx = path.lastIndexOf("/");

        return path.substring(idx + 1);
    }

    public static String upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String convertedFileName = FileService.fileNameConvert(fileName);

        try {
            String mimeType = new Tika().detect(file.getInputStream());
            FileService.checkImageMimeType(mimeType);
        } catch (IOException exception) {
            throw new ImageRoadFailedException();
        }

        return convertedFileName;
    }

}

