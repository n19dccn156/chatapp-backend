package com.project.chatapp.service.account;

import com.project.chatapp.config.exception.ResourceNotFoundException;
import com.project.chatapp.config.exception.UnauthorizedException;
import com.project.chatapp.dto.Enum.EFriendState;
import com.project.chatapp.dto.account.AccountLogin;
import com.project.chatapp.dto.account.AccountMapper;
import com.project.chatapp.dto.account.AccountResponse;
import com.project.chatapp.model.TokenResponse;
import com.project.chatapp.repository.database.account.AccountEntity;
import com.project.chatapp.repository.database.account.AccountRepository;
import com.project.chatapp.repository.database.friend.FriendEntity;
import com.project.chatapp.repository.database.friend.FriendRepository;
import com.project.chatapp.repository.redis.accessToken.CacheAccessToken;
import com.project.chatapp.repository.redis.accessToken.CacheAccessTokenRepository;
import com.project.chatapp.repository.redis.refreshToken.CacheRefreshToken;
import com.project.chatapp.repository.redis.refreshToken.CacheRefreshTokenRepository;
import com.project.chatapp.util.JwtUtil;
import com.project.chatapp.util.PasswordUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountQueryService {

    @NonNull
    final AccountRepository accountRepository;
    @NonNull
    final FriendRepository friendRepository;
    @NonNull
    final CacheAccessTokenRepository accessTokenRepository;
    @NonNull
    final CacheRefreshTokenRepository refreshTokenRepository;
    @NonNull
    final AccountMapper accountMapper;
    @NonNull
    final PasswordUtil passwordUtil;

    public TokenResponse login(AccountLogin loginForm) {
        AccountEntity foundAccount = accountRepository.findByEmail(loginForm.email())
                .orElseThrow(() -> new ResourceNotFoundException("Email hoặc mật khẩu sai"));
        if (!passwordUtil.matches(loginForm.password(), foundAccount.getPassword()))
            throw new ResourceNotFoundException("Email hoặc mật khẩu sai");

        CacheAccessToken newAccessToken = new CacheAccessToken(JwtUtil.generateAccessToken(String.valueOf(foundAccount.getAccountId())), foundAccount.getAccountId(), JwtUtil.ACCESS_TOKEN_EXPIRATION);
        CacheRefreshToken newRefreshToken = new CacheRefreshToken(JwtUtil.generateRefreshToken(String.valueOf(foundAccount.getAccountId())), foundAccount.getAccountId(), JwtUtil.REFRESH_TOKEN_EXPIRATION);

        accessTokenRepository.save(newAccessToken);
        refreshTokenRepository.save(newRefreshToken);
        return new TokenResponse(foundAccount.getAccountId(), newAccessToken.token(), newRefreshToken.token());
    }

    public void auth(String accessToken) {
        accessTokenRepository.findById(accessToken).orElseThrow(UnauthorizedException::new);
    }

    public TokenResponse refreshToken(String refreshToken) {
        CacheRefreshToken foundRefresh = refreshTokenRepository.findById(refreshToken).orElseThrow(UnauthorizedException::new);

        CacheAccessToken newAccessToken = CacheAccessToken.builder()
                .token(JwtUtil.generateAccessToken(String.valueOf(foundRefresh.accountId())))
                .accountId(foundRefresh.accountId())
                .expiration(JwtUtil.ACCESS_TOKEN_EXPIRATION)
                .build();
        CacheRefreshToken newRefreshToken = CacheRefreshToken.builder()
                .token(JwtUtil.generateAccessToken(String.valueOf(foundRefresh.accountId())))
                .accountId(foundRefresh.accountId())
                .expiration(JwtUtil.REFRESH_TOKEN_EXPIRATION)
                .build();

        accessTokenRepository.save(newAccessToken);
        refreshTokenRepository.save(newRefreshToken);
        return new TokenResponse(foundRefresh.accountId(), newAccessToken.token(), newRefreshToken.token());
    }

    public AccountEntity accountEntityById(Integer id) {
        return accountRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public ResponseEntity<AccountResponse> accountById(Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                accountMapper.toDto(accountRepository.findById(id).orElseThrow(ResourceNotFoundException::new))
        );
    }

    public ResponseEntity<AccountResponse> accountById(Integer id, String token) {
        Integer meID = accessTokenRepository.findById(token).orElseThrow(UnauthorizedException::new).accountId();
        if(id.equals(meID)) {
            return ResponseEntity.status(HttpStatus.OK).body(accountMapper.toDto(accountEntityById(id), "ME", null));
        } else {
            Optional<FriendEntity> foundFriend = friendRepository.findByFromIdOrToId(id, meID);
            if(foundFriend.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(accountMapper.toDto(accountEntityById(id), EFriendState.ADD.name(), null));
            } else if(foundFriend.get().getState().equals(EFriendState.ACCEPT)) {
                if(foundFriend.get().getFromId().equals(id)) {
                    return ResponseEntity.status(HttpStatus.OK).body(accountMapper.toDto(accountEntityById(id), EFriendState.ACCEPT.name(), foundFriend.get().getFriendId()));
                }
                return ResponseEntity.status(HttpStatus.OK).body(accountMapper.toDto(accountEntityById(id), EFriendState.AWAIT.name(), foundFriend.get().getFriendId()));
            }
            return ResponseEntity.status(HttpStatus.OK).body(accountMapper.toDto(accountEntityById(id), EFriendState.FRIEND.name(), foundFriend.get().getFriendId()));
        }
    }

    public ResponseEntity<AccountResponse> accountMe(String token) {
        return accountById(accessTokenRepository.findById(token)
            .orElseThrow(UnauthorizedException::new).accountId());
    }

    public ResponseEntity<AccountResponse> accountByEmail(String email, String token) {
        AccountEntity foundEntity = accountRepository.findByEmail(email.toLowerCase()).orElseThrow(ResourceNotFoundException::new);
        return accountById(foundEntity.getAccountId(), token);
    }

    public String getNickname(Integer accountId) {
        return accountRepository.findById(accountId).orElseThrow(ResourceNotFoundException::new).getNickname();
    }
}
