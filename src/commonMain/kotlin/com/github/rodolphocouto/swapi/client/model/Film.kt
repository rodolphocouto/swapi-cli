package com.github.rodolphocouto.swapi.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Film(
    val title: String,
    @SerialName("episode_id") val episodeId: Int,
    @SerialName("opening_crawl") val openingCrawl: String,
    val director: String,
    val producer: String,
    @SerialName("release_date") val releaseDate: String,
    val url: String
)

@Serializable
data class FilmPage(
    override val count: Int,
    override val previous: String?,
    override val next: String?,
    override val results: Array<Film>
) : Page<Film>
