package org.konan.multiplatform;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.gdgvenezia.domain.entities.EventModel
import java.text.SimpleDateFormat
import java.util.*

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
            // TODO: change it
            eventSubtitle.text = "Subtitle"

            dayText.text = item.date.day.toString()

            val date = Date(item.date.epochInSeconds * 1000)
            val formatter = SimpleDateFormat("MMM", Locale.getDefault())
            monthText.text = formatter.format(date)
        }
    }
}
