package com.github.gdgvenezia.data

import com.github.gdgvenezia.domain.entities.EventModel

class MeetupEventResponseMapper {

    fun map(t: EventResponseItemModel): EventModel {
        return EventModel(
                title = t.title,
                monthShort = t.date.monthShort,
                year = t.date.year,
                day = t.date.day,
                description = t.description
        )
    }
}