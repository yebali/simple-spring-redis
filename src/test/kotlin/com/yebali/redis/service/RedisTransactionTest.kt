package com.yebali.redis.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
@Rollback(value = false)
class RedisTransactionTest {
    @Autowired
    lateinit var redisService: RedisService

    @Test
    fun occurExceptionWhileRedisTransaction() {
        val keys = listOf("A", "B")
        redisService.occurExceptionAfterIncreaseFirstValue(keys)
    }

    @Test
    fun occurExceptionAfterRedisTransaction() {
        val keys = listOf("A", "B", "C")
        redisService.increaseValueInTransaction(keys)

        throw RuntimeException("Redis Transaction이 끝난 후 예외를 던지다.")
    }
}
