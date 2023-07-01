package com.project.chatapp.service.account;

import com.project.chatapp.dto.account.AccountMapper;
import com.project.chatapp.dto.account.AccountRegister;
import com.project.chatapp.repository.database.account.AccountEntity;
import com.project.chatapp.repository.database.account.AccountRepository;
import com.project.chatapp.util.PasswordUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCommandService {

    @NonNull final AccountRepository accountRepository;
    @NonNull final AccountMapper accountMapper;
    @NonNull final PasswordUtil passwordUtil;

    public void register(AccountRegister accountRegister) {
        AccountEntity newAccount = accountMapper.toEntity(accountRegister);
        newAccount.setPassword(passwordUtil.encode(accountRegister.password()));
        newAccount.setEmail(accountRegister.email().toLowerCase());
        accountRepository.save(newAccount);
    }
}
