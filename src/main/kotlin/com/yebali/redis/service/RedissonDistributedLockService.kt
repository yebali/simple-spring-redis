package com.yebali.redis.service

import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class RedissonDistributedLockService(
    private val redissonClient: RedissonClient,
) {
    fun <T> operateWithLock(key: String, operation: (RLock) -> T): T {
        redissonClient.getLock(key).let { rLock ->
            if (rLock.tryLock(10, TimeUnit.SECONDS)) {
                try {
                    return operation(rLock)
                } finally {
                    rLock.unlock()
                }
            } else {
                throw Exception("Fail to get Lock")
            }
        }
    }
}
