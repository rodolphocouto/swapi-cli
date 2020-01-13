package com.github.rodolphocouto.swapi.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Starship(
    val name: String,
    val model: String,
    val manufacturer: String,
    @SerialName("cost_in_credits") val costInCredits: String,
    val length: String,
    @SerialName("max_atmosphering_speed") val maxAtmospheringSpeed: String,
    val crew: String,
    val passengers: String,
    @SerialName("cargo_capacity") val cargoCapacity: String,
    val consumables: String,
    @SerialName("hyperdrive_rating") val hyperdriveRating: String,
    @SerialName("MGLT") val mglt: String,
    @SerialName("starship_class") val starshipClass: String,
    val url: String
)

@Serializable
data class StarshipPage(
    override val count: Int,
    override val previous: String?,
    override val next: String?,
    override val results: Array<Starship>
) : Page<Starship>
