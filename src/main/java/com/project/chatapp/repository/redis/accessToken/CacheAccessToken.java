package com.project.chatapp.repository.redis.accessToken;

import lombok.Builder;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Accessors(chain = true)
@RedisHash("cacheAccessToken")
@Builder
public record CacheAccessToken(
        @Id String token,
        @Indexed Integer accountId,
        @TimeToLive Long expiration
) {
}
