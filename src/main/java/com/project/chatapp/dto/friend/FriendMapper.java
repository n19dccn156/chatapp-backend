package com.project.chatapp.dto.friend;

import com.project.chatapp.dto.Enum.EFriendState;
import com.project.chatapp.repository.database.friend.FriendEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FriendMapper {

    FriendEntity toEntity(FriendSave friend, EFriendState state);
}
