package com.yebali.redis.config

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RedissonConfig(
    private val redisProperties: RedisProperties,
) {
    @Bean
    fun redissonClient(): RedissonClient {
        return Redisson.create(
            Config().apply {
                this.useSingleServer().clientName = redisProperties.clientName
                this.useSingleServer().address = "redis://${redisProperties.host}:${redisProperties.port}"
                this.useSingleServer().database = redisProperties.database
                this.useSingleServer().password = null
            }
        )
    }
}
