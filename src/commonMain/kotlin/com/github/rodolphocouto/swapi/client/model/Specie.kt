package com.github.rodolphocouto.swapi.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Specie(
    val name: String,
    val classification: String,
    val designation: String,
    @SerialName("average_height") val averageHeight: String,
    @SerialName("skin_colors") val skinColors: String,
    @SerialName("hair_colors") val hairColors: String,
    @SerialName("eye_colors") val eyeColors: String,
    @SerialName("average_lifespan") val averageLifespan: String,
    val language: String,
    val url: String
)

@Serializable
data class SpeciePage(
    override val count: Int,
    override val previous: String?,
    override val next: String?,
    override val results: Array<Specie>
) : Page<Specie>
