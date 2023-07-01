package com.project.chatapp.service.member;

import com.project.chatapp.repository.database.member.MemberEntity;
import com.project.chatapp.repository.database.member.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberQueryService {

    @NonNull final MemberRepository memberRepository;
    public List<MemberEntity> membersInRoom(String roomId) {
        return memberRepository.findAllByGroupId(roomId);
    }
}
