package com.project.chatapp.service.file;

import com.project.chatapp.config.exception.ResourceNotFoundException;
import com.project.chatapp.repository.database.file.FileEntity;
import com.project.chatapp.repository.database.file.FileRepository;
import com.project.chatapp.util.Const;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileQueryService {
    @NonNull
    final FileRepository fileRepository;
    private final String HOST_FILE = Const.HOST+"/api/files/";

    public byte[] getFile(String fileId) {
        FileEntity foundFile = fileRepository.findById(fileId).orElseThrow(ResourceNotFoundException::new);
        return foundFile.getData();
    }
}
