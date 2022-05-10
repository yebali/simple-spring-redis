package com.yebali.redis.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("spring.redis")
class RedissonProperties(
    val clientName: String,
    val host: String,
    val database: String,
    val port: Int,
    val password: String,
)
