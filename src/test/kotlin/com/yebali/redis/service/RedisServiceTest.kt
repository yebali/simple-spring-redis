package com.yebali.redis.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
@Rollback(value = false)
class RedisServiceTest {
    @Autowired
    lateinit var redisService: RedisService

    @Test
    fun occurExceptionWhileRedisTransaction() {
        val keys = listOf("A", "B")

        redisService.occurExceptionAfterIncreaseFirstValue(keys)
    }

    @Test
    fun occurExceptionAfterRedisTransaction() {
        val keys = listOf("A", "B")

        redisService.increaseValueInTransaction(keys)

        throw RuntimeException("예외를 던지다.")
    }

    @Test
    fun `Redis에서 값 찾기`() {
        val key = "AA"
        val value = "ABCD"
        println("redisService.saveKeyValueAsSet(key, value) = ${redisService.saveOpsSet(key, value)}")
        println("[In Redis] key:$key, value:${redisService.getOpsSetMembers(key)}")
    }
}
