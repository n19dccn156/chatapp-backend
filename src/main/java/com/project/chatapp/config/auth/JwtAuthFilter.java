package com.project.chatapp.config.auth;

import com.project.chatapp.repository.database.account.AccountEntity;
import com.project.chatapp.repository.database.account.AccountRepository;
import com.project.chatapp.repository.redis.accessToken.CacheAccessToken;
import com.project.chatapp.repository.redis.accessToken.CacheAccessTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired AccountRepository accountRepository;
    @Autowired CacheAccessTokenRepository cacheTokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJwtFromRequest(request);
        if(StringUtils.hasText(jwt)) {
            Optional<CacheAccessToken> foundCache = cacheTokenRepository.findById(jwt);
            if(foundCache.isPresent()) {
                Optional<AccountEntity> foundAccount = accountRepository.findById(foundCache.get().accountId());
                if(foundAccount.isPresent()) {
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(foundAccount.get().getRole().name()));
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(foundAccount.get().getEmail(), null, authorities);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
