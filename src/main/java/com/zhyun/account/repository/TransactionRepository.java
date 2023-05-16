package com.zhyun.account.repository;

import com.zhyun.account.domain.Account;
import com.zhyun.account.domain.AccountUser;
import com.zhyun.account.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // JpaRepository<Transaction, Long> : Transaction 엔티티 활용, Long : Transaction의 기본키 타입

    Optional<Transaction> findByTransactionId(String transactionId);
}
