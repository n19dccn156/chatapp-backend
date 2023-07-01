package com.project.chatapp.repository.database.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    void deleteByAccountIdAndGroupId(Integer accountId, String groupId);

    List<MemberEntity> findAllByGroupId(String roomId);
}
