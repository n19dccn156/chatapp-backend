package com.project.chatapp.repository.database.messageFile;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message_files")
public class MessageFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageFileId;
    @Column(name = "message_id")
    private Integer messageId;
    private String url;
}
