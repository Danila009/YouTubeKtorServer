package ru.youTube.features.video.controller

import io.ktor.http.*
import ru.youTube.database.video.dto.CreateVideoDTO
import ru.youTube.database.video.enums.VideoSortingType
import ru.youTube.database.video.model.VideoModel

interface VideoController {

    suspend fun getVideos(
        search:String? = null,
        idGenre:Int? = null,
        pageNumber:Int,
        pageSize:Int,
        sortingType: VideoSortingType?):List<VideoModel>

    suspend fun getVideoById(id:Int):VideoModel

    suspend fun deleteById(id: Int): HttpStatusCode

    suspend fun insert(video: CreateVideoDTO): HttpStatusCode
}