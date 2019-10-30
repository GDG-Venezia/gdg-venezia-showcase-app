package com.github.gdgvenezia

import com.github.gdgvenezia.domain.entities.EventModel

/**
 * @author Andrea Maglie
 */
fun EventModel.format(): String {
    return this.title
}

fun List<EventModel>.format(): String {
    return this.joinToString(", ") { it.title }
}

object Utils {

    fun formatEventList(list: List<EventModel>): String {
        return list.joinToString(", ") { it.title }
    }
}