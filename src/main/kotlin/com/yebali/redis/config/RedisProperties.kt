package com.yebali.redis.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("spring.redis")
class RedisProperties(
    val clientName: String,
    val host: String,
    val database: Int,
    val port: Int,
    val password: String,
)
