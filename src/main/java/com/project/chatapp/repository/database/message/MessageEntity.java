package com.project.chatapp.repository.database.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.chatapp.dto.Enum.EType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class MessageEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer messageId;
  @Column(name = "sender_id")
  private Integer senderId;
  @Column(name = "room_id")
  private String roomId;
  @Enumerated(EnumType.STRING)
  private EType type;
  private String text;
  private String avatar;
  @Column(name = "created_at")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
  private LocalDateTime createdAt = LocalDateTime.now();
}
