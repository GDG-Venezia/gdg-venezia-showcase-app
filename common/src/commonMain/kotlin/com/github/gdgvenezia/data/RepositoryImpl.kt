package com.github.gdgvenezia.data

import com.github.gdgvenezia.domain.entities.SocialLinkModel
import com.github.gdgvenezia.domain.Repository
import com.github.gdgvenezia.domain.entities.EventModel
import com.github.gdgvenezia.domain.entities.PhotoModel
import com.github.gdgvenezia.domain.entities.TeamMemberModel
import kotlinx.serialization.UnstableDefault

/**
 * @author Andrea Maglie
 */
@UnstableDefault
class RepositoryImpl(private val api: Api): Repository {

    private val eventMapper by lazy { MeetupEventResponseMapper() }

    override suspend fun getEventList(): List<EventModel> {
        return api.getEvents().map { eventMapper.map(it) }
    }

    override suspend fun getPastEventList(): List<EventModel> {
        return api.getPastEvents().map { eventMapper.map(it) }
    }

    override suspend  fun getFutureEventList(): List<EventModel> {
        return api.getFutureEvents().map { eventMapper.map(it) }
    }

    override suspend fun getPhotoList(): List<PhotoModel> {
        val photos = api.getPhotos()
        return photos.map { PhotoModel(
                title = it.title,
                url = it.url,
                tags = emptyList()
        ) }
    }

    override suspend fun getTeamMemeberList(): List<TeamMemberModel> {
        return api.getTeam().map {
            TeamMemberModel(
                    firstname = it.firstname,
                    lastname = it.lastname,
                    pictureUrl = it.pictureUrl,
                    shortDescription = it.shortDescription,
                    longDescription = it.longDescription,
                    twitterUrl = it.twitterUrl,
                    linkedinUrl = it.linkedinUrl
            )
        }
    }

    override suspend fun getSocialLinkList(): List<SocialLinkModel> {
        return api.getSocialLinks().map {
            SocialLinkModel(
                    title = it.title,
                    code = it.code,
                    url = it.url
            )
        }
    }
}