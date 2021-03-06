package ru.youTube.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.youTube.auth.AuthConfig
import ru.youTube.auth.TokenProvider
import ru.youTube.auth.common.ConstantsAuth.AUTH_AUDIENCE
import ru.youTube.auth.common.ConstantsAuth.AUTH_ISSUER
import ru.youTube.auth.common.ConstantsAuth.AUTH_MY_REALM
import ru.youTube.auth.common.ConstantsAuth.AUTH_SECRET
import ru.youTube.database.channel.ChannelDAO
import ru.youTube.database.channel.Channels
import ru.youTube.database.genre.VideGenres
import ru.youTube.database.genre.VideoGenreDAO
import ru.youTube.database.subscription.SubscriptionDAO
import ru.youTube.database.subscription.Subscriptions
import ru.youTube.database.user.UserDAO
import ru.youTube.database.user.Users
import ru.youTube.database.video.VideoDAO
import ru.youTube.database.video.Videos
import ru.youTube.database.videoComment.VideoComments
import ru.youTube.database.videoComment.VideoCommentsDAO
import ru.youTube.features.channel.controller.ChannelController
import ru.youTube.features.channel.controller.ChannelControllerImpl
import ru.youTube.features.subscription.controller.SubscriptionController
import ru.youTube.features.subscription.controller.SubscriptionControllerImpl
import ru.youTube.features.user.controller.UserController
import ru.youTube.features.video.controller.VideoController
import ru.youTube.features.video.controller.VideoControllerImpl
import ru.youTube.features.user.controller.UserControllerImpl
import ru.youTube.features.video.videoComment.controller.VideoCommentController
import ru.youTube.features.video.videoComment.controller.VideoCommentControllerImpl
import ru.youTube.features.video.videoGenre.controller.VideoGenreController
import ru.youTube.features.video.videoGenre.controller.VideoGenreControllerImpl

val daoModule = module {
    single<VideoDAO> { Videos }
    single<UserDAO> { Users }
    single<ChannelDAO> { Channels }
    single<VideoCommentsDAO> { VideoComments }
    single<VideoGenreDAO> { VideGenres }
    single<SubscriptionDAO> { Subscriptions }
}

val controllerModule = module {
    singleOf(::VideoControllerImpl) { bind<VideoController>() }
    singleOf(::UserControllerImpl) { bind<UserController>() }
    singleOf(::ChannelControllerImpl) { bind<ChannelController>() }
    singleOf(::VideoCommentControllerImpl) { bind<VideoCommentController>() }
    singleOf(::VideoGenreControllerImpl) { bind<VideoGenreController>() }
    singleOf(::SubscriptionControllerImpl) { bind<SubscriptionController>() }
}

val authModule = module {
    single {
        AuthConfig(
            secret = AUTH_SECRET,
            issuer = AUTH_ISSUER,
            audience = AUTH_AUDIENCE,
            myRealm = AUTH_MY_REALM
        )
    }

    singleOf(::TokenProvider)
}