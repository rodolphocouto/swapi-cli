package com.github.rodolphocouto.swapi.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vehicle(
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
    @SerialName("vehicle_class") val vehicleClass: String,
    val url: String
)

@Serializable
data class VehiclePage(
    override val count: Int,
    override val previous: String?,
    override val next: String?,
    override val results: Array<Vehicle>
) : Page<Vehicle>
