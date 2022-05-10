package com.yebali.redis

import com.yebali.redis.config.RedissonProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(RedissonProperties::class)
@SpringBootApplication
class SimpleRedisApplication

fun main(args: Array<String>) {
    runApplication<SimpleRedisApplication>(*args)
}
