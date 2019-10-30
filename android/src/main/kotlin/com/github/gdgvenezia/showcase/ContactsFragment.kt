package com.github.gdgvenezia.showcase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.github.gdgvenezia.ServiceLocator
import com.github.gdgvenezia.domain.entities.SocialLinkModel
import com.github.gdgvenezia.presentation.social.SocialView
import com.google.android.material.snackbar.Snackbar
import com.github.gdgvenezia.showcase.adapter.ContactsAdapter

class ContactsFragment : Fragment(), SocialView {

    private lateinit var progress: ProgressBar
    private lateinit var mainView: ConstraintLayout
    private lateinit var contactsRecyclerView: RecyclerView

    private val presenter by lazy { ServiceLocator.socialPresenter }

    private val adapter = ContactsAdapter(listOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_contacts, container, false) as ViewGroup
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress = view.findViewById(R.id.CONTACT_progress)
        mainView = view.findViewById(R.id.CONTACT_constraint_layout)
        contactsRecyclerView = view.findViewById(R.id.CONTACT_contact_list)

        contactsRecyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun renderSocialLinkList(photoList: List<SocialLinkModel>) {
        adapter.items = photoList
        adapter.notifyDataSetChanged()
    }

    override fun renderError(errorMessage: String) {
        Snackbar.make(mainView, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun renderLoading(visible: Boolean) {
        if (visible) {
            contactsRecyclerView.visibility = View.GONE
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.GONE
            contactsRecyclerView.visibility = View.VISIBLE
        }
    }
}
