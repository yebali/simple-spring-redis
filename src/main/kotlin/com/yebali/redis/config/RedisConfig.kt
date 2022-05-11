package com.yebali.redis.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
class RedisConfig {
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory()
    }

    @Bean
    fun stringRedisTemplate(): StringRedisTemplate {
        return StringRedisTemplate().apply {
            this.setConnectionFactory(redisConnectionFactory())
            this.setEnableTransactionSupport(true)
        }
    }

    @Bean
    fun transactionManager(): PlatformTransactionManager {
        return JpaTransactionManager()
    }
}
