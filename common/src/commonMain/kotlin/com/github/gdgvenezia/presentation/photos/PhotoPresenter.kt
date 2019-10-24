package com.github.gdgvenezia.presentation.photos

import com.github.gdgvenezia.presentation.BasePresenter
import com.github.gdgvenezia.presentation.BaseView
import com.github.gdgvenezia.domain.Result
import com.github.gdgvenezia.domain.entities.PhotoModel
import com.github.gdgvenezia.domain.usecases.GetPhotoUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author Andrea Maglie
 */
class PhotoPresenter constructor(private val getPhotoUseCase: GetPhotoUseCase,
                                 private val mainScope: CoroutineScope): BasePresenter<PhotoView>(), CoroutineScope by mainScope {

    override fun onViewAttached(view: PhotoView) {
        view.renderLoading(true)
        loadPhotos()
    }

    private fun loadPhotos() {
        launch {
            val result = getPhotoUseCase.execute(Unit)
            view?.renderLoading(false)
            when (result) {
                is Result.Success -> view?.renderPhotoList(result.data)
                is Error -> view?.renderError("Failure")
                else -> {
                    // do nothing
                }
            }
        }
    }
}


interface PhotoView: BaseView {

    fun renderPhotoList(photoList: List<PhotoModel>)

}