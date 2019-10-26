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

    suspend fun getEvents(): List<EventResponseItemModel> {
        return client.get<EventListResponseModel>("$ENDPOINT/events").items
    }

    suspend fun getPastEvents(): List<EventResponseItemModel> {
        return client.get<EventListResponseModel>("$ENDPOINT/events/past").items
    }

    suspend fun getFutureEvents(): List<EventResponseItemModel> {
        return client.get<EventListResponseModel>("$ENDPOINT/events/future").items
    }

    suspend fun getTeam(): List<TeamMemberResponseModel> {
        return client.get<TeamListResponseModel>("$ENDPOINT/team").items
    }

    suspend fun getSocialLinks(): List<SocialLinkResponseModel> {
        return client.get<SocialListResponseModel>("$ENDPOINT/social").items
    }

    suspend fun getPhotosJson(): String {
        return client.get("$ENDPOINT/photos")
    }

}

@Serializable
data class PhotoResponseModel(
    @SerialName("items") val items: List<PhotoResponseItemModel>
)


@Serializable
data class EventListResponseModel(
        @SerialName("items") val items: List<EventResponseItemModel>
)

@Serializable
data class TeamListResponseModel(
        @SerialName("items") val items: List<TeamMemberResponseModel>
)

@Serializable
data class SocialListResponseModel(
        @SerialName("items") val items: List<SocialLinkResponseModel>
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

@Serializable
data class EventResponseItemModel(
        val title: String,
        val time: Long,
        val utcOffset: Long
)

@Serializable
data class TeamMemberResponseModel(
        val firstname: String,
        val lastname: String,
        val pictureUrl: String,
        val shortDescription: String,
        val longDescription: String,
        val twitterUrl: String,
        val linkedinUrl: String
)

@Serializable
data class SocialLinkResponseModel(
        val title: String,
        val code: String,
        val url: String
)

