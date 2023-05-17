package com.zhyun.account.aop;

// 우와 어노테이션 생성!

import java.lang.annotation.*;

@Target(ElementType.METHOD) // 메서드에 붙일 수 있음
@Retention(RetentionPolicy.RUNTIME) // 런타임 시 붙음
@Documented
@Inherited  // 상송 가능
public @interface AccountLock {
    long tryLockTime() default 5000L; // ?
}
