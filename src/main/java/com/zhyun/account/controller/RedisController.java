package com.zhyun.account.controller;

import com.zhyun.account.service.RedissonTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisController {
    private final RedissonTestService redissonTestService;

    @GetMapping("/get-lock")
    public String getLock() {
        return redissonTestService.getLock();
    }

}

