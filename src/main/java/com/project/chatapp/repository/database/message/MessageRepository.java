package com.project.chatapp.repository.database.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {

    List<MessageEntity> findAllByRoomIdOrderByCreatedAtAsc(String roomId);

    List<MessageEntity> findAllByRoomId(String roomId);

    @Query("""
        SELECT m, mf FROM MessageEntity m
        LEFT JOIN MessageFileEntity mf
        ON m.messageId = mf.messageId
        WHERE m.roomId = :roomId
        ORDER BY m.createdAt ASC
    """)
    List<Object[]> messagesByRoom(String roomId);

    @Query(value = """
        SELECT a.room_id, b.account_id, b.name, b.avatar, b.type, a.created_at
        FROM (SELECT room_id, MAX(created_at) created_at FROM messages GROUP BY room_id) a
        INNER JOIN (
            SELECT b.friend_id room_id, b.account_id, a.nickname name, a.avatar, 'FRIEND' type FROM accounts a
            INNER JOIN (
                SELECT f.to_id account_id, f.friend_id FROM friends f
                WHERE f.from_id = :accountId
                UNION
                SELECT f.from_id account_id, f.friend_id FROM friends f
                WHERE f.to_id = :accountId) b
            ON a.account_id = b.account_id
            UNION
            SELECT g.group_id room_id, m.account_id, g.name name, g.avatar, 'GROUP' type FROM members m
            INNER JOIN chatapp.groupss g ON m.group_id = g.group_id
            WHERE m.account_id = :accountId
        ) b ON a.room_id = b.room_id
        ORDER BY a.created_at DESC
    """, nativeQuery = true)
    List<Object[]> listMessageByAccountId(Integer accountId);

    @Query(value = """
        SELECT room_id, MAX(created_at) created_at
        FROM messages
        GROUP BY room_id
        ORDER BY created_at DESC
    """, nativeQuery = true)
    List<Object[]> listCreatedAtByRoom();
}
