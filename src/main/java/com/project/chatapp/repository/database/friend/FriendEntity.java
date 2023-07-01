package com.project.chatapp.repository.database.friend;

import com.project.chatapp.dto.Enum.EFriendState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "friends")
public class FriendEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String friendId;
  @Column(name = "from_id")
  private Integer fromId;
  @Column(name = "to_id")
  private Integer toId;
  @Enumerated(EnumType.STRING)
  private EFriendState state;
}
