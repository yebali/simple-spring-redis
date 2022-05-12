// package com.yebali.redis.config
//
// import org.springframework.context.annotation.Bean
// import org.springframework.context.annotation.Configuration
// import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
// import org.springframework.data.redis.core.RedisTemplate
//
// @Configuration
// class RedisTemplateConfig {
//    @Bean
//    fun redisTemplate(): RedisTemplate<String, String> {
//        return RedisTemplate<String, String>().apply {
//            this.setConnectionFactory(LettuceConnectionFactory())
//            this.setEnableTransactionSupport(true)
//        }
//    }
// }
