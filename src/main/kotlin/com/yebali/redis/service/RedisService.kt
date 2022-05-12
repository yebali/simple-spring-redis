package com.yebali.redis.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RedisService(
    private val redisTemplate: StringRedisTemplate
) {
    /** for controller*/
    fun saveOpsValue(key: String, value: String): Int? {
        return redisTemplate.opsForValue().append(key, value)
    }

    fun saveOpsSet(key: String, value: String): Long? {
        return redisTemplate.opsForSet().add(key, value)
    }

    fun getOpsValue(key: String): String? {
        return redisTemplate.opsForValue().get(key)
    }

    fun getOpsSet(key: String): String? {
        return redisTemplate.opsForSet().pop(key)
    }

    fun getOpsSetMembers(key: String): MutableSet<String>? {
        return redisTemplate.opsForSet().members(key)
    }

    /** for test */
    fun occurExceptionAfterIncreaseFirstValue(keys: List<String>) {
        if (keys.isNotEmpty())
            redisTemplate.opsForValue().increment(keys.first())

        throw RuntimeException()
    }

    fun increaseValueInTransaction(keys: List<String>): List<Long?> {
        return keys.map { key ->
            redisTemplate.opsForValue().increment(key)
        }
    }
}
