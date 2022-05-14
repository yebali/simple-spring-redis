package com.yebali.redis.config

import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
class StringRedisTemplateConfig {
//    아래 StringRedisTemplate를 사용하면 조회가 안된다.....
//    @Bean
//    fun stringRedisTemplate(lettuceConnectionFactory: LettuceConnectionFactory): StringRedisTemplate {
//        return StringRedisTemplate().apply {
//            this.setConnectionFactory(lettuceConnectionFactory)
//            // 아래 옵션으로 Transaction을 사용하면 insert는 되는데 select가 안되는 문제가 발생.
//            // lettuce가 autoConfig해주는 얘를 사용하고 mutil(), exec()를 이용한 Transaction을 사용하자.
//            this.setEnableTransactionSupport(true)
//        }
//    }
}
