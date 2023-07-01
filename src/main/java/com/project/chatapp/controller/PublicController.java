package com.project.chatapp.controller;

import com.project.chatapp.dto.account.AccountLogin;
import com.project.chatapp.dto.account.AccountRegister;
import com.project.chatapp.model.TokenResponse;
import com.project.chatapp.service.account.AccountUseCaseService;
import com.project.chatapp.service.file.FileUseCaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pub")
@Tag(name = "Public", description = "Public API")
@RequiredArgsConstructor
public class PublicController {

    @NonNull
    final AccountUseCaseService accountService;
    @NonNull
    final FileUseCaseService fileUseCaseService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid AccountRegister accountRegister) {
        accountService.register(accountRegister);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid AccountLogin loginForm) {
        return accountService.login(loginForm);
    }

    @GetMapping("/auth")
    public void auth(@RequestParam String accessToken) {
        accountService.auth(accessToken);
    }

    @GetMapping("/refresh")
    public TokenResponse refreshToken(@RequestParam String refreshToken) {
        return accountService.refreshToken(refreshToken);
    }
}
