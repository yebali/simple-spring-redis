package com.yebali.redis.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RedisService(
    private val stringRedisTemplate: StringRedisTemplate,
) {
    @Transactional
    fun occurExceptionAfterIncreaseFirstValue(keys: List<String>) {
        if (keys.isNotEmpty())
            stringRedisTemplate.opsForValue().increment(keys.first())

        throw RuntimeException()
    }

    @Transactional
    fun increaseValueInTransaction(keys: List<String>): List<Long?> {
        return keys.map { key ->
            stringRedisTemplate.opsForValue().increment(key)
        }
    }

    fun getValue(key: String): String {
        return stringRedisTemplate.opsForValue().get(key)
            ?: throw Exception("Could not find value in Redis")
    }
}
