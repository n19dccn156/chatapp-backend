package com.project.chatapp.service.file;

import com.project.chatapp.config.exception.ResourceBadRequestException;
import com.project.chatapp.repository.database.file.FileEntity;
import com.project.chatapp.repository.database.file.FileRepository;
import com.project.chatapp.util.Const;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileCommandService {
    @NonNull
    final FileRepository fileRepository;
    private final String HOST_FILE = Const.HOST+"/api/files/";

    public String saveImage(MultipartFile image) {
        String fileId = String.valueOf(UUID.randomUUID());
        try {
            return fileRepository
                .save(new FileEntity(
                    fileId,
                    image.getBytes(),
                    HOST_FILE + fileId, image.getName(),
                    (int) image.getSize()))
                .getUrl();
        } catch (IOException e) {
            throw new ResourceBadRequestException();
        }
    }

    public List<String> saveImages(MultipartFile[] images) {
        List<FileEntity> listImages = Arrays.stream(images).toList().stream().map(image -> {
            try {
                String fileId = String.valueOf(UUID.randomUUID());
                return new FileEntity(fileId, image.getBytes(), HOST_FILE+fileId, image.getName(), (int) image.getSize());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toList();
        return fileRepository.saveAll(listImages).stream().map(FileEntity::getUrl).toList();
    }
}
