package com.zhyun.account.service;

import ch.qos.logback.core.pattern.color.RedCompositeConverter;
import com.zhyun.account.exception.AccountException;
import com.zhyun.account.type.ErrorCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LockServiceTest {

    @Mock
    private RedissonClient redissonClient;

    @Mock
    private RLock rLock; // RLock = 우리가 만든 bean은 아니지만, rLock 동작을 어떻게 설정하느냐에 따라 우리 로직이 변하기 때문에 rLock을 목킹해서 rLock의 동작을 우리가 원하는 대로 설정해볼 계획

    @InjectMocks
    private LockService lockService;

    @Test
    void successGetLock() throws InterruptedException {
        // given
        given(redissonClient.getLock(anyString()))
                .willReturn(rLock);
        given(rLock.tryLock(anyLong(), anyLong(), any()))
                .willReturn(true);

        // when
        // then
        assertDoesNotThrow(() -> lockService.lock("123"));
    }

    @Test
    void failGetLock() throws InterruptedException {
        // given
        given(redissonClient.getLock(anyString()))
                .willReturn(rLock);
        given(rLock.tryLock(anyLong(), anyLong(), any()))
                .willReturn(false);

        // when
        AccountException accountException = assertThrows(AccountException.class, () -> lockService.lock("123"));

        // then
        assertEquals(ErrorCode.ACCOUNT_TRANSACTION_LOCK, accountException.getErrorCode());
    }
}