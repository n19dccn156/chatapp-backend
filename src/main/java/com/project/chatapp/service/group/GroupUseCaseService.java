package com.project.chatapp.service.group;

import com.project.chatapp.dto.account.Sender;
import com.project.chatapp.dto.group.GroupRequest;
import com.project.chatapp.dto.group.GroupResponse;
import com.project.chatapp.dto.group.GroupSave;
import com.project.chatapp.dto.message.MessageResponse;
import com.project.chatapp.service.message.MessageCommandService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class GroupUseCaseService {

    @NonNull final GroupCommandService groupCommandService;
    @NonNull final GroupQueryService groupQueryService;

    @Transactional
    public MessageResponse createGroup(String room, GroupSave group) {
        return groupCommandService.createGroup(room, group);
    }

    @Transactional
    public MessageResponse addMember(String room, GroupRequest group) {
        return groupCommandService.addMember(room, group);
    }

    @Transactional
    public MessageResponse kickMember(String room, GroupRequest group) {
        return groupCommandService.kickMember(room, group);
    }

    @Transactional
    public MessageResponse leaveGroup(String room, Sender sender) {
        return groupCommandService.leaveGroup(room, sender);
    }
}
