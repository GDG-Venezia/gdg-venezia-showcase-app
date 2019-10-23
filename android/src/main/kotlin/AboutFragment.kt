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
import com.github.gdgvenezia.domain.entities.TeamMemberModel
import com.github.gdgvenezia.presentation.events.TeamView
import com.google.android.material.snackbar.Snackbar
import org.konan.multiplatform.adapter.TeamAdapter

class AboutFragment : Fragment(), TeamView {

    private lateinit var progress: ProgressBar
    private lateinit var mainView: ConstraintLayout
    private lateinit var teamRecyclerView: RecyclerView
    private lateinit var aboutText: TextView
    private lateinit var viewGroup: Group

    private val teamAdapter = TeamAdapter(listOf())

    private val presenter by lazy { ServiceLocator.teamPresenter }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_about, container, false) as ViewGroup
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress = view.findViewById(R.id.ABOUT_progress)
        mainView = view.findViewById(R.id.ABOUT_constraint_layout)
        teamRecyclerView = view.findViewById(R.id.ABOUT_team_list)
        aboutText = view.findViewById(R.id.ABOUT_text_about)
        viewGroup = view.findViewById(R.id.ABOUT_view_group)

        teamRecyclerView.adapter = teamAdapter
    }

    override fun renderTeam(team: List<TeamMemberModel>) {

        // TODO: show real text
        aboutText.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

        teamAdapter.items = team
        teamAdapter.notifyDataSetChanged()
    }

    override fun renderError(errorMessage: String) {
        Snackbar.make(mainView, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun renderLoading(visible: Boolean) {
        if (visible) {
            viewGroup.visibility = View.GONE
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.GONE
            viewGroup.visibility = View.VISIBLE
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