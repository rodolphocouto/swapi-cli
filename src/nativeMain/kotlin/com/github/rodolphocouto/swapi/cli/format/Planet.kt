package com.github.rodolphocouto.swapi.cli.format

import com.github.rodolphocouto.swapi.client.model.Planet

fun Planet.format() =
    """
        |           name: $name
        |rotation period: $rotationPeriod
        | orbital period: $orbitalPeriod
        |       diameter: $diameter
        |        climate: $climate
        |        gravity: $gravity
        |        terrain: $terrain
        |  surface water: $surfaceWater
        |     population: $population
    """.trimMargin("|")

fun Planet?.format() = this?.format() ?: "Planet not found :("

fun Iterable<Planet>.formatToString() = this.joinToString(separator = "\n\n") { it.format() }
