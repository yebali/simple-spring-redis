package com.yebali.redis.config

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RedissonConfig(
    private val redissonProperties: RedissonProperties,
) {
    @Bean
    fun redissonClient(): RedissonClient {
        return Redisson.create(
            Config().apply {
                this.useSingleServer().address = "redis://${redissonProperties.host}:${redissonProperties.port}"
            }
        )
    }
}
