package com.project.chatapp.repository.database.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, String> {

    Optional<FileEntity> findByUrl(String url);
}
