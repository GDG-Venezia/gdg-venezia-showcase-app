package com.github.gdgvenezia

import com.github.gdgvenezia.coroutines.CustomMainScope
import com.github.gdgvenezia.data.Api
import com.github.gdgvenezia.presentation.team.TeamPresenter
import com.github.gdgvenezia.data.RepositoryImpl
import com.github.gdgvenezia.domain.usecases.GetEventListUseCase
import com.github.gdgvenezia.domain.usecases.GetPhotoUseCase
import com.github.gdgvenezia.domain.usecases.GetSocialLinkListUseCase
import com.github.gdgvenezia.domain.usecases.GetTeamUseCase
import com.github.gdgvenezia.presentation.events.EventListPresenter
import com.github.gdgvenezia.presentation.photos.PhotoPresenter
import com.github.gdgvenezia.presentation.social.SocialPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.UnstableDefault
import kotlin.native.concurrent.ThreadLocal

/**
 * @author Andrea Maglie
 */
@UnstableDefault
@ThreadLocal
object ServiceLocator {

    val api = Api()

    val repository = RepositoryImpl(api)

    val customMainScope = CustomMainScope()

    val eventListPresenter: EventListPresenter
        get() = EventListPresenter(GetEventListUseCase(repository),
                customMainScope)

    val teamPresenter: TeamPresenter
        get() = TeamPresenter(GetTeamUseCase(repository),
                customMainScope)

    val photoPresenter: PhotoPresenter
        get() = PhotoPresenter(
                getPhotoUseCase = GetPhotoUseCase(repository = repository),
                mainScope = CustomMainScope()
        )

    val socialPresenter: SocialPresenter
        get() = SocialPresenter(GetSocialLinkListUseCase(repository),
                customMainScope)

}
