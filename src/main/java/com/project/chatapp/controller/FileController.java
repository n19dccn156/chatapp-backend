package com.project.chatapp.controller;

import com.project.chatapp.service.file.FileUseCaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/files")
@Tag(name = "File", description = "File API")
@RequiredArgsConstructor
public class FileController {

    @NonNull
    final FileUseCaseService fileUseCaseService;

    @PostMapping
    public List<String> saveImages(@RequestParam("image") MultipartFile[] images) {
        return fileUseCaseService.saveImages(images);
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<byte[]> getFile(@PathVariable String fileId) {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(fileUseCaseService.getFile(fileId));
    }

    @PostMapping("/image")
    public String saveImage(@RequestParam("image") MultipartFile image) {
        return fileUseCaseService.saveImage(image);
    }

}
