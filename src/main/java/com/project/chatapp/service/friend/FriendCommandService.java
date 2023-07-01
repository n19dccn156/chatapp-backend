package com.project.chatapp.service.friend;

import com.project.chatapp.config.exception.ResourceNotFoundException;
import com.project.chatapp.dto.Enum.EFriendState;
import com.project.chatapp.dto.Enum.EType;
import com.project.chatapp.dto.account.Sender;
import com.project.chatapp.dto.friend.FriendSave;
import com.project.chatapp.dto.message.MessageMapper;
import com.project.chatapp.dto.message.MessageResponse;
import com.project.chatapp.repository.database.friend.FriendEntity;
import com.project.chatapp.repository.database.friend.FriendRepository;
import com.project.chatapp.service.account.AccountUseCaseService;
import com.project.chatapp.service.message.MessageUseCaseService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FriendCommandService {

    @NonNull final FriendRepository friendRepository;
    @NonNull final MessageUseCaseService messageService;
    @NonNull final AccountUseCaseService accountService;

    public MessageResponse addFriend(String room, FriendSave friend) {
        String text = accountService.getNickname(friend.sender().accountId()) + " đã gửi lời mời kết bạn";

        friendRepository.save(new FriendEntity(room, friend.sender().accountId(), friend.toId(), EFriendState.ADD));
        return messageService.save(friend.sender().accountId(), room, EType.SYSTEM, text, friend.sender().avatar(), null);
    }

    public MessageResponse acceptFriend(String room, Sender sender) {
        String text = accountService.getNickname(sender.accountId()) + " đã đồng ý kết bạn";
        FriendEntity foundFriend = friendRepository.findById(room).orElseThrow(ResourceNotFoundException::new);
        foundFriend.setState(EFriendState.FRIEND);

        friendRepository.save(foundFriend);
        return messageService.save(sender.accountId(), room, EType.SYSTEM, text, sender.avatar(), null);
    }

    public MessageResponse deleteFriend(String room, Sender sender) {
        String text = accountService.getNickname(sender.accountId()) + " đã huỷ kết bạn";

        friendRepository.deleteById(room);
        return messageService.save(sender.accountId(), room, EType.SYSTEM, text, sender.avatar(), null);
    }
}
