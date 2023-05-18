package com.zhyun.account.aop;

// 어노테이션 생성!

import java.lang.annotation.*;

@Target(ElementType.METHOD) // 이 어노테이션은 메서드에 붙일 수 있음
@Retention(RetentionPolicy.RUNTIME) // 이 어노테이션은 런타임 시 붙음
@Documented // 문서화시 내용에 꼭 포함됨
@Inherited  // 이 어노테이션이 붙은 클래스를 상속하면 어노테이션도 상속 됨
public @interface AccountLock {
    long tryLockTime() default 5000L; // 이 메서드의 기본값은 5000L이다.
}
