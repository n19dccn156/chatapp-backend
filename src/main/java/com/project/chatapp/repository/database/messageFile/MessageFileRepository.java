package com.project.chatapp.repository.database.messageFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageFileRepository extends JpaRepository<MessageFileEntity, Integer> {

    @Query("""
        SELECT mf.url FROM MessageFileEntity mf
        WHERE mf.messageId IN (SELECT m.messageId FROM MessageEntity m WHERE m.roomId = :room)
    """)
    List<String> imagesInRoom(String room);
}
