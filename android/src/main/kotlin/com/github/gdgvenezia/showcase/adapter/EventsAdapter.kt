package com.github.gdgvenezia.showcase.adapter

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.recyclerview.widget.RecyclerView
import com.github.gdgvenezia.domain.entities.EventModel
import com.github.gdgvenezia.showcase.R

class EventsAdapter(var items: List<EventModel>) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: EventModel) {

            val eventTitle = itemView.findViewById<TextView>(R.id.ITEM_EVENT_title)
            val eventSubtitle = itemView.findViewById<TextView>(R.id.ITEM_EVENT_subtitle)
            val dayText = itemView.findViewById<TextView>(R.id.ITEM_EVENT_day)
            val monthText = itemView.findViewById<TextView>(R.id.ITEM_EVENT_month)

            eventTitle.text = item.title
            if (item.eventDescription.isNotEmpty()) {
                eventSubtitle.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(item.eventDescription, FROM_HTML_MODE_COMPACT)
                } else {
                    @Suppress("DEPRECATION")
                    Html.fromHtml(item.eventDescription)
                }
            }
            dayText.text = item.day.toString()
            monthText.text = item.monthShort
        }
    }
}
