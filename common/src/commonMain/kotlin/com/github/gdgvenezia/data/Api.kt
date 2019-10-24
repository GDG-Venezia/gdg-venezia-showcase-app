package com.github.gdgvenezia.data

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor
import kotlinx.serialization.json.Json

/**
 * @author Andrea Maglie
 */
//internal expect val ApplicationDispatcher: CoroutineDispatcher

private const val ENDPOINT = "https://thawing-ridge-92338.herokuapp.com"

class Api {

    private val client by lazy {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json.nonstrict)
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.BODY
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

    suspend fun getPhotos(): List<PhotoResponseItemModel> {
        return client.get<PhotoResponseModel>("$ENDPOINT/photos").items
    }

    suspend fun getPhotosJson(): String {
        return client.get("$ENDPOINT/photos")
    }

}

@Serializable
data class PhotoResponseModel(
    @SerialName("items") val items: List<PhotoResponseItemModel>
)

/*
@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
@Serializer(PhotoResponseModel::class)
object PhotoResponseModelSerializer: KSerializer<PhotoResponseModel> {

    override val descriptor = StringDescriptor.withName("PhotoResponseModel")

    override fun serialize(output: Encoder, obj: PhotoResponseModel) {
        PhotoResponseItemModel.serializer().list.serialize(output, obj.items)
    }

    override fun deserialize(input: Decoder): PhotoResponseModel {
        return PhotoResponseModel(PhotoResponseItemModel.serializer().list.deserialize(input))
    }
}
*/

@Serializable
data class PhotoResponseItemModel(
        val title: String,
        val url: String
        //val tags: List<String>?
)