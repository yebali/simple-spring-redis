package com.yebali.redis.service

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalTime

@SpringBootTest
class RedissonDistributedLockServiceTest {
    @Autowired
    lateinit var redissonDistributedLockService: RedissonDistributedLockService

    @Test
    fun testDistributedLock() {
        runBlocking {
            launch {
                repeat(20) {
                    redissonDistributedLockService.operateWithLock("lock_key") {
                        println("now = ${LocalTime.now()}")
                        Thread.sleep(300)
                    }
                }
            }
        }
    }
}
