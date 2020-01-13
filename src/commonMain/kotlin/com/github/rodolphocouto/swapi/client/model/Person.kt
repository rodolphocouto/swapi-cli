package com.github.rodolphocouto.swapi.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val name: String,
    val height: String,
    val mass: String,
    @SerialName("hair_color") val hairColor: String,
    @SerialName("skin_color") val skinColor: String,
    @SerialName("eye_color") val eyeColor: String,
    @SerialName("birth_year") val birthYear: String,
    val gender: String
)

@Serializable
data class PersonPage(
    override val count: Int,
    override val previous: String?,
    override val next: String?,
    override val results: Array<Person>
) : Page<Person>
