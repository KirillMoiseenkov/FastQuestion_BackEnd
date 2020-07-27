package com.rob.FastQuestion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileSaverService {

    private static String PATH = "/opt";

    @Autowired
    QuestionFileStorageService questionFileStorageService;

    public String saveFile(String fileName, MultipartFile file) {
        String path = PATH + fileName;
        try (FileOutputStream fos = new FileOutputStream(path)) {
            byte[] buffer = file.getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return path;
    }

    public void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }
    }
}
