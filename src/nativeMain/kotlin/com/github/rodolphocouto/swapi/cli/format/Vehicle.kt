package com.github.rodolphocouto.swapi.cli.format

import com.github.rodolphocouto.swapi.client.model.Vehicle

fun Vehicle.format() =
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
        |        vehicle class: $vehicleClass
    """.trimMargin("|")

fun Vehicle?.format() = this?.format() ?: "Vehicle not found :("

fun Iterable<Vehicle>.formatToString() = this.joinToString(separator = "\n\n") { it.format() }
