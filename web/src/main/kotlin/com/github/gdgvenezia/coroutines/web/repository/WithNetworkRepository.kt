package com.github.gdgvenezia.coroutines.web.repository

import org.example.kotlin.multiplatform.repository.NetworkRepository
import react.RProps

external interface WithNetworkRepository : RProps {
    var networkRepository: NetworkRepository
}
