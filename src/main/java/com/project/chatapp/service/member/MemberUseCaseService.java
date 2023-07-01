package com.project.chatapp.service.member;

import com.project.chatapp.dto.Enum.ERole;
import com.project.chatapp.repository.database.member.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberUseCaseService {

    @NonNull final MemberCommandService memberCommandService;
    @NonNull final MemberQueryService memberQueryService;

    public void save(Integer accountId, String room, ERole role) {
        memberCommandService.save(accountId, room, role);
    }

    public void delete(Integer accountId, String groupId) {
        memberCommandService.delete(accountId, groupId);
    }

    public List<MemberEntity> membersInRoom(String roomId) {
        return memberQueryService.membersInRoom(roomId);
    }
}
