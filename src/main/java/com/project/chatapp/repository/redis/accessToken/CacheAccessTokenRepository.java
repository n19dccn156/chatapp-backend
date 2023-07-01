package com.project.chatapp.repository.redis.accessToken;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheAccessTokenRepository extends CrudRepository<CacheAccessToken, String> {
}
