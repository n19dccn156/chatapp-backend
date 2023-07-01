package com.project.chatapp.dto.message;

import com.project.chatapp.repository.database.message.MessageEntity;
import com.project.chatapp.repository.database.messageFile.MessageFileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(source = "entity", target = "message")
    @Mapping(source = "files", target = "files")
    MessageResponse toDto(MessageEntity entity, List<String> files);
}
