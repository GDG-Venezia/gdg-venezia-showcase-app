package com.github.gdgvenezia.domain.entities

/**
 * @author Andrea Maglie
 */
data class EventModel(
        val title: String,
        val day: Int,
        val monthShort: String,
        val year: Int,
        val description: String
)
