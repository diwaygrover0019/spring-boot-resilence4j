package com.diway.springbootresilence4j.service

import com.diway.springbootresilence4j.models.UpstreamResponse
import com.diway.springbootresilence4j.models.User
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.ratelimiter.RateLimiter
import io.github.resilience4j.ratelimiter.RateLimiterConfig
import io.github.resilience4j.ratelimiter.RateLimiterRegistry
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.time.Duration
import java.util.function.Supplier


/**
 * @Author Diway on 20/10/2021
 */
@Service
class DemoService {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Value("\${downstream.base}")
    lateinit var baseUrl: String

    @Value("\${downstream.url}")
    lateinit var downstreamUrl: String

    @Autowired
    lateinit var restTemplate: RestTemplate

    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackForUsers")
    fun getUpstreamResponse(): UpstreamResponse? {
        return restTemplate.getForObject(baseUrl.plus(downstreamUrl), UpstreamResponse::class.java)
    }

    fun fallbackForUsers(t: Throwable): UpstreamResponse {
        log.info("Inside fallbackForUsers, cause - $t")

        return UpstreamResponse(
            users = arrayListOf(
                User(id = "fallback-id", name = "fallback-name")),
            isError = true
        )
    }
}