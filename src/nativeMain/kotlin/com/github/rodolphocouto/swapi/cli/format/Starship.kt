package com.github.rodolphocouto.swapi.cli.format

import com.github.rodolphocouto.swapi.client.model.Starship

fun Starship.format() =
    """
        |                  name: $name
        |                 model: $model
        |          manufacturer: $manufacturer
        |       cost in credits: $costInCredits
        |                length: $length
        |max atmosphering speed: $maxAtmospheringSpeed
        |                  crew: $crew
        |            passengers: $passengers
        |        cargo capacity: $cargoCapacity
        |           consumables: $consumables
        |     hyperdrive rating: $hyperdriveRating
        |                  MGLT: $mglt
        |        starship class: $starshipClass
    """.trimMargin("|")

fun Starship?.format() = this?.format() ?: "Starship not found :("

fun Iterable<Starship>.formatToString() = this.joinToString(separator = "\n\n") { it.format() }
