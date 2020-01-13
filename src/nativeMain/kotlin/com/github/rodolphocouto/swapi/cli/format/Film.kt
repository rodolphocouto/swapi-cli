package com.github.rodolphocouto.swapi.cli.format

import com.github.rodolphocouto.swapi.client.model.Film

fun Film.format() =
    """
        |        title: $title
        |   episode id: $episodeId
        |     director: $director
        |     producer: $producer
        | release date: $releaseDate
        |opening crawl: ${openingCrawl.replace("\r\n", "\n               ")}
        |          url: $url
    """.trimMargin("|")

fun Film?.format() = this?.format() ?: "Film not found :("

fun Iterable<Film>.formatToString() = this.joinToString(separator = "\n\n") { it.format() }
