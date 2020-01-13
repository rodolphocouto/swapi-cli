package com.github.rodolphocouto.swapi.cli.output

import com.github.rodolphocouto.swapi.client.model.Person

fun Person.toOutput() =
    """
        |      name: $name
        |    height: $height
        |      mass: $mass
        |hair color: $hairColor
        |skin color: $skinColor
        | eye color: $eyeColor
        |birth year: $birthYear
        |       url: $url
    """.trimMargin("|")

fun Person?.toOutput() = this?.toOutput() ?: "Person not found :("

fun Iterable<Person>.joinToOutput() = this.joinToString(separator = "\n\n") { it.toOutput() }
