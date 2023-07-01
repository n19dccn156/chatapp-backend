package com.project.chatapp.repository.redis.refreshToken;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheRefreshTokenRepository extends CrudRepository<CacheRefreshToken, String> {
}