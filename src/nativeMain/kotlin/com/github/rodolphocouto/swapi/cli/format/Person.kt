package com.github.rodolphocouto.swapi.cli.format

import com.github.rodolphocouto.swapi.client.model.Person

fun Person.format() =
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

fun Person?.format() = this?.format() ?: "Person not found :("

fun Iterable<Person>.formatToString() = this.joinToString(separator = "\n\n") { it.format() }
