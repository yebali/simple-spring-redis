package com.yebali.redis.service

import org.redisson.api.RedissonClient
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class RedissonDistributedLockService(
    private val redissonClient: RedissonClient,
) {
    fun operateWithLock(operation: () -> Unit) {
        val lock = redissonClient.getLock("distributed-lock-key")

        if (lock.tryLock(10, TimeUnit.SECONDS)) {
            try {
                operation()
            } finally {
                lock.unlock()
            }
        } else {
            throw Exception("Fail to get Lock")
        }
    }
}
