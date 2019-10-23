package com.github.gdgvenezia.domain.entities

/**
 * @author Andrea Maglie
 */
data class EventModel(
        val title: String,
        val date: EventDate
)


data class EventDate(
        val day: Int,
        val month: Int,
        val year: Int,
        val hour: Int,
        val minute: Int = 0,
        val epochInSeconds: Long
)
