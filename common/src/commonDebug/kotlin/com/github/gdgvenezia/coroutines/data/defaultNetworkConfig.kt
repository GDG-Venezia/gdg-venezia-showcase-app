package com.github.gdgvenezia.coroutines.data

data class NetworkConfig(
    val host: String = "localhost",
    val port: Int = 8080,
    val secure: Boolean = false
)

