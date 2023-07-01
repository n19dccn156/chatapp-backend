package com.project.chatapp.repository.database.account;

import com.project.chatapp.dto.Enum.EGender;
import com.project.chatapp.dto.Enum.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;
    private String email;
    private String password;
    private String nickname;
    @Enumerated(EnumType.STRING)
    private EGender gender = EGender.MALE;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private ERole role = ERole.USER;
    @Column(name = "is_active")
    private Boolean isActive = true;
}
