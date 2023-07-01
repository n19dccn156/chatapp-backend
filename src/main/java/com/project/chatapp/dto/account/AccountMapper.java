package com.project.chatapp.dto.account;

import com.project.chatapp.repository.database.account.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountEntity toEntity(AccountRegister accountRegister);
    AccountResponse toDto(AccountEntity entity);
    AccountResponse toDto(AccountEntity entity, String state, String friendId);
}
