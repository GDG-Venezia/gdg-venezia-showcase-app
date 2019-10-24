package com.github.gdgvenezia.presentation.events

import com.github.gdgvenezia.domain.entities.EventListModel
import com.github.gdgvenezia.presentation.BasePresenter
import com.github.gdgvenezia.presentation.BaseView
import com.github.gdgvenezia.domain.Result
import com.github.gdgvenezia.domain.usecases.GetEventListUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author Andrea Maglie
 */
class EventListPresenter constructor(private val getEventListUseCase: GetEventListUseCase): BasePresenter<EventListView>() {

    override fun onViewAttached(view: EventListView) {
        view.renderLoading(true)
        getEventList()
    }

    private fun getEventList() {
        GlobalScope.launch {
            val result = getEventListUseCase.execute(Unit)
            view?.renderLoading(false)
            when (result) {
                is Result.Success -> view?.renderEventList(result.data)
                is Error -> view?.renderError("Failure")
            }
        }
    }
}


interface EventListView: BaseView {

    fun renderEventList(eventList: EventListModel)

}