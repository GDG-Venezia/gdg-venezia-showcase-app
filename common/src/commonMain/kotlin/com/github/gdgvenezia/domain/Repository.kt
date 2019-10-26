package com.github.gdgvenezia.domain

import com.github.gdgvenezia.domain.entities.SocialLinkModel
import com.github.gdgvenezia.domain.entities.PhotoModel
import com.github.gdgvenezia.domain.entities.EventModel
import com.github.gdgvenezia.domain.entities.TeamMemberModel

/**
 * @author Andrea Maglie
 */
interface Repository {
    suspend fun getEventList(): List<EventModel>
    suspend fun getPastEventList(): List<EventModel>
    suspend fun getFutureEventList(): List<EventModel>
    fun getTeamMemeberList(): List<TeamMemberModel>
    suspend fun getPhotoList(): List<PhotoModel>
    fun getSocialLinkList(): List<SocialLinkModel>
}