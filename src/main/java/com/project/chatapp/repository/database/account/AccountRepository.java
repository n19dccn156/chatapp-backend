package com.project.chatapp.repository.database.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    Optional<AccountEntity> findByEmail(String email);
}
