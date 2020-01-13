package com.github.rodolphocouto.swapi.cli.output

import com.github.rodolphocouto.swapi.client.model.Film

fun Film.toOutput() =
    """
        |        title: $title
        |   episode id: $episodeId
        |     director: $director
        |     producer: $producer
        | release date: $releaseDate
        |opening crawl: ${openingCrawl.replace("\r\n", "\n               ")}
        |          url: $url
    """.trimMargin("|")

fun Film?.toOutput() = this?.toOutput() ?: "Film not found :("

fun Iterable<Film>.joinToOutput() = this.joinToString(separator = "\n\n") { it.toOutput() }
