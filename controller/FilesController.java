package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.UploadResponseMessage;
import com.example.demo.service.FileService;


@RestController
@RequestMapping("files")
public class FilesController {

    private final FileService fileService;

    @Autowired
    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<UploadResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws BadRequestException, AlreadyExistsException {
        fileService.save(file, name);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
    }
    
    @PostMapping("/update")
    public ResponseEntity<UploadResponseMessage> updateFile(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws BadRequestException {
        fileService.update(file, name);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
    }
    
    @GetMapping("/{image}")
    public ResponseEntity<?> getImage(@PathVariable String image) throws IOException{
    	Path imagePath = Paths.get("/Users/Edon/Documents/\\SpringBootProjects/demo/src/main/resources/static/images/" + image);
    	ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(imagePath));
    	return ResponseEntity
                .ok()
                .contentLength(imagePath.toFile().length())
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
