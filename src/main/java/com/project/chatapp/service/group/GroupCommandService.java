package com.project.chatapp.service.group;

import com.project.chatapp.dto.Enum.ERole;
import com.project.chatapp.dto.Enum.EType;
import com.project.chatapp.dto.account.Sender;
import com.project.chatapp.dto.group.GroupRequest;
import com.project.chatapp.dto.group.GroupSave;
import com.project.chatapp.dto.message.MessageMapper;
import com.project.chatapp.dto.message.MessageResponse;
import com.project.chatapp.repository.database.group.GroupEntity;
import com.project.chatapp.repository.database.group.GroupRepository;
import com.project.chatapp.repository.database.member.MemberEntity;
import com.project.chatapp.repository.database.member.MemberRepository;
import com.project.chatapp.repository.database.message.MessageEntity;
import com.project.chatapp.repository.database.message.MessageRepository;
import com.project.chatapp.service.account.AccountUseCaseService;
import com.project.chatapp.service.member.MemberUseCaseService;
import com.project.chatapp.service.message.MessageUseCaseService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GroupCommandService {

    @NonNull final GroupRepository groupRepository;
    @NonNull final MemberUseCaseService memberService;
    @NonNull final AccountUseCaseService accountService;
    @NonNull final MessageUseCaseService messageService;

    public MessageResponse createGroup(String room, GroupSave group) {
        String text = "Bạn đã tạo nhóm " + group.name();
        groupRepository.save(new GroupEntity(room, group.name(), group.avatar(), null));
        memberService.save(group.membersId(), room, ERole.ADMIN);
        return messageService.save(group.sender().accountId(), room, EType.SYSTEM, text, group.sender().avatar(), null);
    }

    public MessageResponse addMember(String room, GroupRequest group) {
        String nickname = accountService.getNickname(group.accountId());
        String text = String.format("đã mời %s vào nhóm", nickname);
        memberService.save(group.accountId(), room, ERole.ADMIN);
        return messageService.save(group.sender().accountId(), room, EType.SYSTEM, text, group.sender().avatar(), null);
    }

    public MessageResponse kickMember(String room, GroupRequest group) {
        String nickname = accountService.getNickname(group.sender().accountId());
        String nickname2 = accountService.getNickname(group.accountId());
        String text = String.format("%s đã mời %s vào nhóm",nickname, nickname2);
        memberService.save(group.accountId(), room, ERole.ADMIN);
        return messageService.save(group.sender().accountId(), room, EType.SYSTEM, text, group.sender().avatar(), null);
    }

    public MessageResponse leaveGroup(String room, Sender sender) {
        String text = accountService.getNickname(sender.accountId()) + " đã rời khỏi nhóm";
        memberService.delete(sender.accountId(), room);
        return messageService.save(sender.accountId(), room, EType.SYSTEM, text, sender.avatar(), null);

    }
}
