package com.yebali.redis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan("com.yebali.redis")
@SpringBootApplication
class SimpleRedisApplication

fun main(args: Array<String>) {
    runApplication<SimpleRedisApplication>(*args)
}
