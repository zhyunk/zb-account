package com.zhyun.account.service;

import com.zhyun.account.aop.AccountLockIdInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LockAopAspect {
    private final LockService lockService;

    @Around("@annotation(com.zhyun.account.aop.AccountLock) && args(request)") // aspectJ 문법으로 우리가 어떤 경우에 이 aspect를 적용할 것인가 정의하고 어노테이션 인자로 requeset를 입력받음
    public Object aroundMethod(
            ProceedingJoinPoint pjp,
            AccountLockIdInterface request
    ) throws Throwable {    // pjp.proceed에서 예외가 발생할 수 있기 때문에 throws 설정
        // lock 취득 시도
        lockService.lock(accountNumber);
        try {
            // before
            return pjp.proceed(); // aop를 걸어준 부분을 동작 시킴
            // after

        } finally {
            // aop걸어준 부분이 정상/실패 구분없이 동작한 후 lock 해제
            lockService.unlock(accountNumber);
        }
    }

}
