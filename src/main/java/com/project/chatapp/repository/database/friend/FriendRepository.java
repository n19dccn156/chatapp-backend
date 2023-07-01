package com.project.chatapp.repository.database.friend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<FriendEntity, String> {
    @Query("""
        SELECT f FROM FriendEntity f
        WHERE (f.fromId = :fromId AND f.toId = :toId)
            OR (f.fromId = :toId AND f.toId = :fromId)
    """)
    Optional<FriendEntity> findByFromIdOrToId(Integer fromId, Integer toId);
}
