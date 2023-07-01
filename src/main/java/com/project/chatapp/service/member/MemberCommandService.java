package com.project.chatapp.service.member;

import com.project.chatapp.dto.Enum.ERole;
import com.project.chatapp.repository.database.member.MemberEntity;
import com.project.chatapp.repository.database.member.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberCommandService {

    @NonNull final MemberRepository memberRepository;

    public void save(Integer accountId, String room, ERole role) {
        memberRepository.save(new MemberEntity(null, accountId, room, role.name()));
    }

    public void delete(Integer accountId, String groupId) {
        memberRepository.deleteByAccountIdAndGroupId(accountId, groupId);
    }
}
