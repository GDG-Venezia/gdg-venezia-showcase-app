package com.github.gdgvenezia.domain.entities

/**
 * @author Andrea Maglie
 */
data class EventListModel(
        val pastEvents: List<EventModel>,
        val futureEvents: List<EventModel>
)