package com.github.gdgvenezia.presentation.events

import com.github.gdgvenezia.domain.entities.EventListModel
import com.github.gdgvenezia.presentation.BaseView

interface EventListView: BaseView {

    fun renderEventList(eventList: EventListModel)

}