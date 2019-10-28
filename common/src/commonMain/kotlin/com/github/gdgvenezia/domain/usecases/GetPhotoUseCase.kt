package com.github.gdgvenezia.domain.usecases

import com.github.gdgvenezia.domain.*
import com.github.gdgvenezia.domain.entities.PhotoModel
import com.github.gdgvenezia.domain.entities.TeamMemberModel

/**
 * @author Andrea Maglie
 */
class GetPhotoUseCase constructor(private val repository: Repository): UseCase<Unit, List<PhotoModel>> {

    override suspend fun execute(params: Unit): Result<List<PhotoModel>> {
        return try {
            val team = repository.getPhotoList()
            Result.Success(team)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
