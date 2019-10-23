package org.konan.multiplatform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.github.gdgvenezia.ServiceLocator
import com.github.gdgvenezia.domain.entities.EventListModel
import com.github.gdgvenezia.presentation.events.EventListView
import com.google.android.material.snackbar.Snackbar


class EventsFragment : Fragment(), EventListView {

    private lateinit var progress: ProgressBar
    private lateinit var pastRecyclerView: RecyclerView
    private lateinit var futureRecyclerView: RecyclerView
    private lateinit var listGroup: Group
    private lateinit var mainView: ConstraintLayout
    private lateinit var futureTitle: TextView

    private val pastEventsAdapter = EventsAdapter(mutableListOf())
    private val futureEventsAdapter = EventsAdapter(mutableListOf())

    private val presenter by lazy { ServiceLocator.eventListPresenter }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_events, container, false) as ViewGroup
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress = view.findViewById(R.id.EVENTS_progress)
        pastRecyclerView = view.findViewById(R.id.EVENTS_past_list)
        futureRecyclerView = view.findViewById(R.id.EVENTS_future_list)
        listGroup = view.findViewById(R.id.EVENTS_list_group)
        mainView = view.findViewById(R.id.EVENTS_constraint_layout)
        futureTitle = view.findViewById(R.id.EVENTS_future_title)

        futureRecyclerView.isNestedScrollingEnabled = false
        futureRecyclerView.adapter = futureEventsAdapter

        pastRecyclerView.isNestedScrollingEnabled = false
        pastRecyclerView.adapter = pastEventsAdapter

    }

    override fun renderEventList(eventList: EventListModel) {
        if (eventList.futureEvents.isNullOrEmpty()) {
            futureTitle.visibility = View.GONE
            futureRecyclerView.visibility = View.GONE
        } else {
            futureEventsAdapter.items = eventList.futureEvents
        }
        pastEventsAdapter.items = eventList.pastEvents

        pastEventsAdapter.notifyDataSetChanged()
        futureEventsAdapter.notifyDataSetChanged()
    }

    override fun renderError(errorMessage: String) {
       Snackbar.make(mainView, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun renderLoading(visible: Boolean) {
        if (visible) {
            listGroup.visibility = View.GONE
            progress.visibility = View.VISIBLE
        } else {
            listGroup.visibility = View.VISIBLE
            progress.visibility = View.GONE
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }
}