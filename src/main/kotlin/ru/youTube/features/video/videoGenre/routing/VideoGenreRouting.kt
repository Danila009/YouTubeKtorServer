package ru.youTube.features.video.videoGenre.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ru.youTube.features.video.videoGenre.controller.VideoGenreController

fun Routing.configureVideoGenreRouting() {

    val videoGenreCollection = inject<VideoGenreController>().value

    route("/api/genre"){

        get {
            val response = videoGenreCollection.getGenres()
            call.respond(response)
        }

        get("{id}") {
            val id = call.parameters["id"]!!.toInt()
            val response = videoGenreCollection.getGenreById(id)
            call.respond(response)
        }
    }
}