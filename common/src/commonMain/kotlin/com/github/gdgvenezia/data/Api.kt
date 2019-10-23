package com.github.gdgvenezia.data

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.Serializable

/**
 * @author Andrea Maglie
 */
//internal expect val ApplicationDispatcher: CoroutineDispatcher

private const val ENDPOINT = "https://thawing-ridge-92338.herokuapp.com/"

class Api {

    private val client by lazy {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }

    /*
    fun getPhotos(callback: (List<PhotoResponseModel>) -> Unit) {
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                val result: List<PhotoResponseModel> = client.get {
                    url("$ENDPOINT/photos")
                }

                callback(result)
            }
        }
    }
    */

    suspend fun getPhotos(): List<PhotoResponseModel> {
        return client.get {"$ENDPOINT/photos"}
    }

}

@Serializable
data class PhotoResponseModel(
        val title: String,
        val url: String,
        val tags: List<String>?
)