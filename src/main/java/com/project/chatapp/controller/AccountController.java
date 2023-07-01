package com.project.chatapp.controller;

import com.project.chatapp.dto.account.AccountResponse;
import com.project.chatapp.dto.message.MessageResponse;
import com.project.chatapp.model.MessageOfAccount;
import com.project.chatapp.repository.database.member.MemberEntity;
import com.project.chatapp.service.account.AccountUseCaseService;
import com.project.chatapp.service.member.MemberUseCaseService;
import com.project.chatapp.service.message.MessageUseCaseService;
import com.project.chatapp.service.messageFile.MessageFileUseCaseService;
import com.project.chatapp.util.HandleHeader;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Account", description = "Account API")
@RequiredArgsConstructor
public class AccountController {

    @NonNull final AccountUseCaseService accountService;
    @NonNull final MessageUseCaseService messageService;
    @NonNull final MessageFileUseCaseService messageFileService;
    @NonNull final MemberUseCaseService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> accountById(@PathVariable Integer id, HttpServletRequest request) {
        return accountService.accountById(id, HandleHeader.getJwtFromRequest(request));
    }

    @GetMapping("/me")
    public ResponseEntity<AccountResponse> accountMe(HttpServletRequest request) {
        return accountService.accountMe(HandleHeader.getJwtFromRequest(request));
    }

    @GetMapping
    public ResponseEntity<AccountResponse> accountByEmail(@RequestParam String email, HttpServletRequest request) {
        return accountService.accountByEmail(email, HandleHeader.getJwtFromRequest(request));
    }

    @GetMapping("/{accountId}/messages")
    public List<MessageOfAccount> messages(@PathVariable Integer accountId) {
        return messageService.message(accountId);
    }

    @GetMapping("/{accountId}/room/{roomId}")
    public MessageOfAccount messageInfo(@PathVariable Integer accountId, @PathVariable String roomId) {
        return messageService.messageInfo(accountId, roomId);
    }

    @GetMapping("/room/{roomId}")
    public List<MessageResponse> messageRoom(@PathVariable String roomId) {
        return messageService.message(roomId);
    }

    @GetMapping("/room/{roomId}/images")
    public List<String> imagesInRoom(@PathVariable String roomId) {
        return messageFileService.imagesInRoom(roomId);
    }

    @GetMapping("/room/{roomId}/members")
    public List<MemberEntity> membersInRoom(@PathVariable String roomId) {
        return memberService.membersInRoom(roomId);
    }
}
