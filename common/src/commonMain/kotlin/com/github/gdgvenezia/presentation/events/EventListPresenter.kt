package com.github.gdgvenezia.presentation.events

import com.github.gdgvenezia.domain.Result
import com.github.gdgvenezia.domain.usecases.GetEventListUseCase
import com.github.gdgvenezia.presentation.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author Andrea Maglie
 */
class EventListPresenter constructor(private val getEventListUseCase: GetEventListUseCase,
                                     private val mainScope: CoroutineScope): BasePresenter<EventListView>(), CoroutineScope by mainScope {

    override fun onViewAttached(view: EventListView) {
        view.renderLoading(true)
        getEventList()
    }

    private fun getEventList() {
        launch {
            val result = getEventListUseCase.execute(Unit)
            view?.renderLoading(false)
            when (result) {
                is Result.Success -> view?.renderEventList(result.data)
                is Error -> view?.renderError("Failure")
            }
        }
    }
}


