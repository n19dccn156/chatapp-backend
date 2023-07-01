package com.project.chatapp.repository.database.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "members")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;
    @Column(name = "account_id")
    private Integer accountId;
    @Column(name = "group_id")
    private String groupId;
    private String role;
}
