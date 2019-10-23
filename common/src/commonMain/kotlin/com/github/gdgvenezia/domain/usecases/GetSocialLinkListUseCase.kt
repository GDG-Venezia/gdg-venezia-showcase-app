package com.github.gdgvenezia.domain.usecases

import com.github.gdgvenezia.domain.entities.SocialLinkModel
import com.github.gdgvenezia.domain.*
import com.github.gdgvenezia.domain.entities.PhotoModel
import com.github.gdgvenezia.domain.entities.TeamMemberModel

/**
 * @author Andrea Maglie
 */
class GetSocialLinkListUseCase constructor(private val repository: Repository): UseCase<Unit, List<SocialLinkModel>> {

    override suspend fun execute(params: Unit): Result<List<SocialLinkModel>> {
        return try {
            val team = repository.getSocialLinkList()
            Result.Success(team)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
