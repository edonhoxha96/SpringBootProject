package com.example.demo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;


@Service
public class FileService {

    @Value("${upload.path}")
    private String uploadPath;

    public void save(MultipartFile file, String name) throws BadRequestException, AlreadyExistsException {
        try {
            Path root = Paths.get(uploadPath);
            String filename = name +"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            Path resolve = root.resolve(filename);
            if (resolve.toFile()
                       .exists()) {
                throw new AlreadyExistsException("File already exists: " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), resolve);
        } catch (Exception e) {
            throw new BadRequestException("Could not store the file. Error: " + e.getMessage());
        }
    }
    
    public void update(MultipartFile file, String name) throws BadRequestException {
        try {
            Path root = Paths.get(uploadPath);
            String filename = name +"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            Path resolve = root.resolve(filename);
            Files.deleteIfExists(resolve);
            Files.copy(file.getInputStream(), resolve);
        } catch (Exception e) {
            throw new BadRequestException("Could not store the file. Error: " + e.getMessage());
        }
    }
}
