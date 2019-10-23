package com.github.gdgvenezia.domain.usecases

import com.github.gdgvenezia.domain.entities.EventListModel
import com.github.gdgvenezia.domain.*
import com.github.gdgvenezia.domain.entities.EventModel

/**
 * @author Andrea Maglie
 */
class GetEventListUseCase constructor(private val repository: Repository): UseCase<Unit, EventListModel> {

    override suspend fun execute(params: Unit): Result<EventListModel> {
        return try {
            val pastEventList = repository.getPastEventList()
            val futureEventList = repository.getFutureEventList()
            Result.Success(EventListModel(
                    pastEvents = pastEventList,
                    futureEvents = futureEventList
            ))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
