package com.project.chatapp.service.file;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileUseCaseService {
    @NonNull
    final FileCommandService fileCommandService;
    @NonNull
    final FileQueryService fileQueryService;

    public String saveImage(MultipartFile image) {
        return fileCommandService.saveImage(image);
    }

    public byte[] getFile(String fileId) {
        return fileQueryService.getFile(fileId);
    }

    public List<String> saveImages(MultipartFile[] images) {
        return fileCommandService.saveImages(images);
    }
}
