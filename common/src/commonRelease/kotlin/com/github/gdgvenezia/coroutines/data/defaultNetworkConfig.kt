package com.github.gdgvenezia.coroutines.data

data class NetworkConfig(
    val host: String = "example.org",
    val port: Int = 0,
    val secure: Boolean = true
)
