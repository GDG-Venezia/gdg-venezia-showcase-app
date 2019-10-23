package com.github.gdgvenezia.domain.usecases

import com.github.gdgvenezia.domain.*
import com.github.gdgvenezia.domain.entities.TeamMemberModel

/**
 * @author Andrea Maglie
 */
class GetTeamUseCase constructor(private val repository: Repository): UseCase<Unit, List<TeamMemberModel>> {

    override suspend fun execute(params: Unit): Result<List<TeamMemberModel>> {
        return try {
            val team = repository.getTeamMemeberList()
            Result.Success(team)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
