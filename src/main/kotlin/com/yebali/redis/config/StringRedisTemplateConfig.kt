// package com.yebali.redis.config
//
// import org.springframework.context.annotation.Bean
// import org.springframework.context.annotation.Configuration
// import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
// import org.springframework.data.redis.core.StringRedisTemplate
// import org.springframework.orm.jpa.JpaTransactionManager
// import org.springframework.transaction.PlatformTransactionManager
// import javax.persistence.EntityManagerFactory
//
// @Configuration
// class StringRedisTemplateConfig(
//    private val entityManagerFactory: EntityManagerFactory,
// ) {
//    @Bean
//    fun stringRedisTemplate(lettuceConnectionFactory: LettuceConnectionFactory): StringRedisTemplate {
//        return StringRedisTemplate().apply {
//            this.setConnectionFactory(lettuceConnectionFactory)
//            this.setEnableTransactionSupport(true)
//
//            this.afterPropertiesSet()
//        }
//    }
//
//    @Bean
//    fun transactionManager(): PlatformTransactionManager {
//        return JpaTransactionManager(entityManagerFactory)
//    }
// }
