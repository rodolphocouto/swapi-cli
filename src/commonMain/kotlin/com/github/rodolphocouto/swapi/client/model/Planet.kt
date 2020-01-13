package com.github.rodolphocouto.swapi.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Planet(
    val name: String,
    @SerialName("rotation_period") val rotationPeriod: String,
    @SerialName("orbital_period") val orbitalPeriod: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    @SerialName("surface_water") val surfaceWater: String,
    val population: String,
    val url: String
)

@Serializable
data class PlanetPage(
    override val count: Int,
    override val previous: String?,
    override val next: String?,
    override val results: Array<Planet>
) : Page<Planet>
