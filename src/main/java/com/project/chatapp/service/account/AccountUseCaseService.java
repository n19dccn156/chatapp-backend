package com.project.chatapp.service.account;

import com.project.chatapp.dto.account.AccountLogin;
import com.project.chatapp.dto.account.AccountRegister;
import com.project.chatapp.dto.account.AccountResponse;
import com.project.chatapp.model.TokenResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountUseCaseService {

    @NonNull final AccountCommandService accountCommandService;
    @NonNull final AccountQueryService accountQueryService;
    @Transactional
    public void register(AccountRegister accountRegister) {
        accountCommandService.register(accountRegister);
    }

    public TokenResponse login(AccountLogin loginForm) {
        return accountQueryService.login(loginForm);
    }

    public void auth(String accessToken) {
        accountQueryService.auth(accessToken);
    }

    public TokenResponse refreshToken(String refreshToken) {
        return accountQueryService.refreshToken(refreshToken);
    }

    public ResponseEntity<AccountResponse> accountById(Integer id, String token) {
        return accountQueryService.accountById(id, token);
    }

    public ResponseEntity<AccountResponse> accountMe(String token) {
        return accountQueryService.accountMe(token);
    }

    public ResponseEntity<AccountResponse> accountByEmail(String email, String token) {
        return accountQueryService.accountByEmail(email, token);
    }

    public String getNickname(Integer accountId) {
        return accountQueryService.getNickname(accountId);
    }
}
