package com.yebali.redis.controller

import com.yebali.redis.service.RedisService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RedisController(
    private val redisService: RedisService,
) {
    @PostMapping("/redis-value")
    fun saveKeyValue(
        @RequestBody request: ReqBody
    ): Int? {
        return redisService.saveOpsValue(
            request.key,
            request.value,
        )
    }

    @PostMapping("/redis-set")
    fun saveKeyValueAsSet(
        @RequestBody request: ReqBody
    ): Long? {
        return redisService.saveOpsSet(
            request.key,
            request.value,
        )
    }

    @GetMapping("/redis-value/{key}")
    fun getKeyValue(
        @PathVariable key: String,
    ): String {
        println("redisService.getOpsValue(key) = ${redisService.getOpsValue(key)}")
        val result = redisService.getOpsSet(key) ?: "null"

        return result
    }

    @GetMapping("/redis-set/{key}")
    fun getKeyValueAsSet(
        @PathVariable key: String,
    ): Set<String>? {
        println("redisService.getOpsSetMembers(key) = ${redisService.getOpsSetMembers(key)}")
        return redisService.getOpsSetMembers(key)
    }

    class ReqBody(
        val key: String,
        val value: String,
    )
}
