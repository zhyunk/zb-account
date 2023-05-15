package com.zhyun.account.repository;

import com.zhyun.account.domain.Account;
import com.zhyun.account.domain.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // JpaRepository<Account, Long> : Account 엔티티 활용, Long : Account의 기본키 타입

    Optional<Account> findFirstByOrderByIdDesc();

    Integer countByAccountUser (AccountUser accountUser);

    Optional<Account> findByAccountNumber(String accountNumber);
}
