package com.github.rodolphocouto.swapi.cli.format

import com.github.rodolphocouto.swapi.client.model.Specie

fun Specie.format() =
    """
        |            name: $name
        |  classification: $classification
        |     designation: $designation
        |  average height: $averageHeight
        |     skin colors: $skinColors
        |     hair colors: $hairColors
        |      eye colors: $eyeColors
        |average lifespan: $averageLifespan
        |        language: $language
    """.trimMargin("|")

fun Specie?.format() = this?.format() ?: "Specie not found :("

fun Iterable<Specie>.formatToString() = this.joinToString(separator = "\n\n") { it.format() }
