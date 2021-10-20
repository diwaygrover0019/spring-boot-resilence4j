package com.diway.springbootresilence4j.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.boot.web.client.RootUriTemplateHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriTemplateHandler
import java.net.URLEncoder


/**
 * @Author Diway on 20/10/2021
 */
@Configuration
class RestClientConfig {

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        //val uriTemplateHandler: UriTemplateHandler = RootUriTemplateHandler(URLEncoder.encode(baseUrl, "UTF-8"))
        return builder
            //.uriTemplateHandler(uriTemplateHandler)
            .build()
    }
}