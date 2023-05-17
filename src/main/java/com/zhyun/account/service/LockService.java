package com.zhyun.account.service;

import com.zhyun.account.exception.AccountException;
import com.zhyun.account.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class LockService {
    private final RedissonClient redissonClient; // RequiredArgsConstructor가 private final 찾아서 생성자에 넣어줌

    public void lock(String accountNumber) {
        RLock lock = redissonClient.getLock(getLockKey(accountNumber));
        log.debug("Trying lock for accountNumber : {}", accountNumber);

        try {
            boolean isLock = lock.tryLock(1, 15, TimeUnit.SECONDS); // waitTime : lock 취득 소요 시간 , leaseTime : lock이 자동으로 해제되는 시간 ,
            if (!isLock) {
                log.error("==================== Lock acquistion failed ==================");
                throw new AccountException(ErrorCode.ACCOUNT_TRANSACTION_LOCK);
            }
        } catch (AccountException e) {
            throw e;
        } catch (Exception e) {
            log.error("Redis lock failed");
        }
    }

    public void unlock(String accountNumber) {
        log.debug("Unlock for accountNumber : {}", accountNumber);
        redissonClient.getLock(getLockKey(accountNumber)).unlock();
    }
    private static String getLockKey(String accountNumber) {
        return "ACLK:" + accountNumber; // ACLK : ACcount Lock Key
    }
}
