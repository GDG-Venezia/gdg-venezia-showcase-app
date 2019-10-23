package org.konan.multiplatform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.github.gdgvenezia.ServiceLocator
import com.github.gdgvenezia.domain.entities.PhotoModel
import com.github.gdgvenezia.presentation.photos.PhotoView
import com.google.android.material.snackbar.Snackbar
import org.konan.multiplatform.adapter.PhotoAdapter

class PhotoFragment : Fragment(), PhotoView {

    private lateinit var progress: ProgressBar
    private lateinit var mainView: ConstraintLayout
    private lateinit var photoRecyclerView: RecyclerView

    private val presenter by lazy { ServiceLocator.photoPresenter }

    private val adapter = PhotoAdapter(listOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_photo, container, false) as ViewGroup
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress = view.findViewById(R.id.PHOTO_progress)
        mainView = view.findViewById(R.id.PHOTO_constraint_layout)
        photoRecyclerView = view.findViewById(R.id.PHOTO_photo_list)

        photoRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        photoRecyclerView.adapter = adapter
    }

    override fun renderPhotoList(photoList: List<PhotoModel>) {
        adapter.items = photoList
        adapter.notifyDataSetChanged()
    }

    override fun renderError(errorMessage: String) {
        Snackbar.make(mainView, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun renderLoading(visible: Boolean) {
        if (visible) {
            mainView.visibility = View.GONE
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.GONE
            mainView.visibility = View.VISIBLE
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