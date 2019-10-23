@file:UseExperimental(KtorExperimentalLocationsAPI::class)

package com.github.gdgvenezia.coroutines.backend

import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import org.example.kotlin.multiplatform.api.Api

@Location("/{trail...}")
data class IndexPage(val trail: List<String>?)

@Location(Api.path)
class Api {
    @Location(V1.path)
    class V1 {
        @Location("${V1.Paths.greeting}/{who?}")
        data class HelloEndpoint(val who: String?)
    }
}
