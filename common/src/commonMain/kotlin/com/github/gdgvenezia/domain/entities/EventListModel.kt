package com.github.gdgvenezia.domain.entities

import com.github.gdgvenezia.domain.entities.EventModel

/**
 * @author Andrea Maglie
 */
data class EventListModel(
        val pastEvents: List<EventModel>,
        val futureEvents: List<EventModel>
)