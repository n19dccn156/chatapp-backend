package com.project.chatapp.repository.database.file;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files")
public class FileEntity {
  @Id
  private String fileId;
  @Lob
  private byte[] data;
  private String url;
  private String name;
  private Integer size;
}
