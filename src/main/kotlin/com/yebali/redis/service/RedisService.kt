package com.yebali.redis.service

import org.springframework.dao.DataAccessException
import org.springframework.data.redis.core.RedisOperations
import org.springframework.data.redis.core.SessionCallback
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ZSetOperations
import org.springframework.stereotype.Service

@Service
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

    fun saveOpsList(key: String, value: String): Long? {
        return redisTemplate.opsForList().leftPush(key, value)
    }

    fun saveOpsZSet(key: String, value: String, score: Double): Boolean? {
        return redisTemplate.opsForZSet().add(key, value, score)
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

    fun getOpsList(key: String, count: Long): List<String>? {
        return redisTemplate.opsForList().rightPop(key, count)
    }

    fun getOpsZSet(key: String, count: Long): MutableSet<ZSetOperations.TypedTuple<String>>? {
        return redisTemplate.opsForZSet().distinctRandomMembersWithScore(key, count)
    }

    /** for test */
    fun occurExceptionAfterIncreaseFirstValue(keys: List<String>) {
        val key = requireNotNull(keys.firstOrNull()) { "T.T" }

        RedisTransaction.transaction(redisTemplate) {
            opsForValue().increment(key)
            throw RuntimeException()
        }
    }

    fun increaseValueInTransaction(keys: List<String>) {
        RedisTransaction.transaction(redisTemplate) {
            keys.map { key ->
                redisTemplate.opsForValue().increment(key)
            }
        }
    }
}

object RedisTransaction {
    fun transaction(operations: StringRedisTemplate, commands: StringRedisTemplate.() -> Unit) {
        operations.execute(object : SessionCallback<Void?> {
            @Throws(DataAccessException::class)
            override fun <K, V> execute(callbackOperations: RedisOperations<K, V>): Void? {
                callbackOperations.multi()
                commands.invoke(operations)
                callbackOperations.exec()
                return null
            }
        })
    }
}
