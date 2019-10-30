package com.github.gdgvenezia.presentation.team

import com.github.gdgvenezia.presentation.BasePresenter
import com.github.gdgvenezia.presentation.BaseView
import com.github.gdgvenezia.domain.Result
import com.github.gdgvenezia.domain.entities.TeamMemberModel
import com.github.gdgvenezia.domain.usecases.GetTeamUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author Andrea Maglie
 */
class TeamPresenter constructor(private val getTeamUseCase: GetTeamUseCase,
                                private val mainScope: CoroutineScope): BasePresenter<TeamView>(), CoroutineScope by mainScope {

    override fun onViewAttached(view: TeamView) {
        view.renderLoading(true)
        getTeam()
    }

    private fun getTeam() {
        launch {
            val result = getTeamUseCase.execute(Unit)
            view?.renderLoading(false)
            when (result) {
                is Result.Success -> view?.renderTeam(result.data)
                is Error -> view?.renderError("Failure")
                else -> {
                    // do nothing
                }
            }
        }
    }
}


interface TeamView: BaseView {

    fun renderTeam(team: List<TeamMemberModel>)

}