package com.diway.springbootresilence4j.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

/**
 * @Author Diway on 20/10/2021
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class UpstreamResponse(
    @JsonProperty("users")
    val users: List<User>,
    val isError: Boolean = false
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class User(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("name")
    val name: String
)