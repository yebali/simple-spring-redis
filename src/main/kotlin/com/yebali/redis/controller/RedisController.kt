package com.yebali.redis.controller

import com.yebali.redis.service.RedisService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RedisController(
    private val redisService: RedisService,
) {

    /** Insert To Redis */
    @PostMapping("/redis-value")
    fun saveValue(
        @RequestBody request: ReqBody
    ) = redisService.saveOpsValue(
        request.key,
        request.value,
    )

    @PostMapping("/redis-set")
    fun saveSet(
        @RequestBody request: ReqBody
    ) = redisService.saveOpsSet(
        request.key,
        request.value,
    )

    @PostMapping("/redis-list")
    fun saveList(
        @RequestBody request: ReqBody,
    ) = redisService.saveOpsList(
        request.key,
        request.value,
    )

    @PostMapping("/redis-zset")
    fun saveZSet(
        @RequestBody request: ReqBody,
    ) = redisService.saveOpsZSet(
        request.key,
        request.value,
        request.score,
    )

    /** Inquiry From Redis*/
    @GetMapping("/redis-value/{key}")
    fun getValue(
        @PathVariable key: String,
    ) = redisService.getOpsValue(key)

    @GetMapping("/redis-set/{key}")
    fun getSet(
        @PathVariable key: String,
    ) = redisService.getOpsSet(key)

    @GetMapping("/redis-set-members/{key}")
    fun getSetMembers(
        @PathVariable key: String,
    ) = redisService.getOpsSetMembers(key)

    @GetMapping("/redis-list/{key}")
    fun getList(
        @PathVariable key: String,
        @RequestParam size: Long,
    ) = redisService.getOpsList(key, size)

    @GetMapping("redis-zset/{key}")
    fun getZSetMax(
        @PathVariable key: String,
        @RequestParam size: Long,
    ) = redisService.getOpsZSet(key, size)

    class ReqBody(
        val key: String,
        val value: String,
        val score: Double = 0.0,
    )
}
