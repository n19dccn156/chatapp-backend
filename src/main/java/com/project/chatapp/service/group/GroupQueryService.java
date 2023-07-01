package com.project.chatapp.service.group;

import com.project.chatapp.config.exception.ResourceNotFoundException;
import com.project.chatapp.dto.group.GroupResponse;
import com.project.chatapp.repository.database.group.GroupEntity;
import com.project.chatapp.repository.database.group.GroupRepository;
import com.project.chatapp.repository.database.member.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GroupQueryService {

    @NonNull
    final GroupRepository groupRepository;
    @NonNull
    final MemberRepository memberRepository;
}