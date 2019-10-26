package com.github.gdgvenezia.presentation.social

import com.github.gdgvenezia.domain.entities.SocialLinkModel
import com.github.gdgvenezia.presentation.BasePresenter
import com.github.gdgvenezia.presentation.BaseView
import com.github.gdgvenezia.domain.Result
import com.github.gdgvenezia.domain.entities.PhotoModel
import com.github.gdgvenezia.domain.entities.TeamMemberModel
import com.github.gdgvenezia.domain.usecases.GetPhotoUseCase
import com.github.gdgvenezia.domain.usecases.GetSocialLinkListUseCase
import com.github.gdgvenezia.domain.usecases.GetTeamUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author Andrea Maglie
 */
class SocialPresenter constructor(private val getSocialLinkListUseCase: GetSocialLinkListUseCase,
                                  private val mainScope: CoroutineScope): BasePresenter<SocialView>(), CoroutineScope by mainScope{

    override fun onViewAttached(view: SocialView) {
        view.renderLoading(true)
        getTeam()
    }

    private fun getTeam() {
        launch {
            val result = getSocialLinkListUseCase.execute(Unit)
            view?.renderLoading(false)
            when (result) {
                is Result.Success -> view?.renderSocialLinkList(result.data)
                is Error -> view?.renderError("Failure")
                else -> {
                    // do nothing
                }
            }
        }
    }
}


interface SocialView: BaseView {

    fun renderSocialLinkList(photoList: List<SocialLinkModel>)

}