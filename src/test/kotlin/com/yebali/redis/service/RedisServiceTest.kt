package com.yebali.redis.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
@Rollback(false)
class RedisServiceTest {
    @Autowired
    lateinit var redisService: RedisService

    // redis는 rollback을 지원하지 않는다.
    // [https://redis.io/docs/manual/transactions/]
    // [https://spring.getdocs.org/en-US/spring-data-docs/spring-data-redis/reference/redis/tx.html]
    // [https://gompangs.tistory.com/entry/Spring-Redis-Template-Transaction]
    // [https://wildeveloperetrain.tistory.com/137]

    // ABCD insert 도중 에러가 난 경우
    @Test
    fun occurExceptionWhileRedisTransaction() {
        val keys = listOf("A", "B")

        Assertions.assertThatExceptionOfType(RuntimeException::class.java).isThrownBy {
            redisService.occurExceptionAfterIncreaseFirstValue(keys)
        }

        // Transaction 내에서 튕기면 저장 X
    }

    // ABCD insert가 모두 끝나고 에러가 난 경우.
    @Test
    fun occurExceptionAfterRedisTransaction() {
        val keys = listOf("A", "B", "C", "D")

        // Redis에 저장
        redisService.increaseValueInTransaction(keys)

        // Redis에서 조회
        keys.forEach { key -> println("[In Redis] key:$key, value:${redisService.getValue(key)}") }

        throw RuntimeException("예외를 던지다.")

        // 저장 후 예외가 발생 할 경우, Rollback이 발생하지 않아
    }
}
