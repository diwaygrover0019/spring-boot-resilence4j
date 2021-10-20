package com.diway.springbootresilence4j.controller

import com.diway.springbootresilence4j.models.UpstreamResponse
import com.diway.springbootresilence4j.service.DemoService
import io.github.resilience4j.ratelimiter.RateLimiter
import io.github.resilience4j.ratelimiter.RateLimiterConfig
import io.github.resilience4j.ratelimiter.RateLimiterRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Duration

/**
 * @Author Diway on 20/10/2021
 */
@RestController
@RequestMapping("/v1")
class DemoController {

    @Autowired
    private lateinit var demoService: DemoService

    @GetMapping("/users", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getResponse(): UpstreamResponse? {
        var config = RateLimiterConfig.custom()
            .limitForPeriod(1)
            .limitRefreshPeriod(Duration.ofMillis(500))
            .timeoutDuration(Duration.ofSeconds(1))
            .build()

        var registry = RateLimiterRegistry.of(config)
        var limiter = registry.rateLimiter("usersLimiter", config)

        var userSupplier = RateLimiter.decorateSupplier(limiter) { demoService.getUpstreamResponse() }
        return userSupplier.get()

        //return demoService.getUpstreamResponse()
    }
}