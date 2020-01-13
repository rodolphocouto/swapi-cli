package com.github.rodolphocouto.swapi.client

import com.github.rodolphocouto.swapi.client.model.Film
import com.github.rodolphocouto.swapi.client.model.FilmPage
import com.github.rodolphocouto.swapi.client.model.Page
import com.github.rodolphocouto.swapi.client.model.Person
import com.github.rodolphocouto.swapi.client.model.PersonPage
import com.github.rodolphocouto.swapi.client.model.Specie
import com.github.rodolphocouto.swapi.client.model.SpeciePage
import com.github.rodolphocouto.swapi.client.model.Starship
import com.github.rodolphocouto.swapi.client.model.StarshipPage
import com.github.rodolphocouto.swapi.client.model.Vehicle
import com.github.rodolphocouto.swapi.client.model.VehiclePage
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode.Companion.NotFound
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json

private const val BASE_URI = "https://swapi.co/api"

class Resource<T>(
    private val client: HttpClient,
    path: String,
    private val response: suspend (HttpResponse) -> T,
    private val pageResponse: suspend (HttpResponse) -> Page<T>
) {

    private val uri = "$BASE_URI$path"

    suspend fun count() = pageResponse(client.get(uri)).count

    suspend fun list() = listAll(uri)

    suspend fun search(text: String) = listAll("$uri?search=$text")

    suspend fun get(id: Int): T? {
        val response = client.get<HttpResponse>("$uri/$id")
        return if (response.status == NotFound) null else response(response)
    }

    private suspend fun listAll(uri: String, results: List<T> = emptyList()): List<T> {
        val response = pageResponse(client.get(uri))
        val allResults = results + response.results

        return response.next?.let { listAll(it, allResults) } ?: allResults
    }
}

@UnstableDefault
class SwapiClient {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json.nonstrict)
        }
    }

    val people = Resource(client, "/people", { it.receive<Person>() }, { it.receive<PersonPage>() })
    val films = Resource(client, "/films", { it.receive<Film>() }, { it.receive<FilmPage>() })
    val starships = Resource(client, "/starships", { it.receive<Starship>() }, { it.receive<StarshipPage>() })
    val vehicles = Resource(client, "/vehicles", { it.receive<Vehicle>() }, { it.receive<VehiclePage>() })
    val species = Resource(client, "/species", { it.receive<Specie>() }, { it.receive<SpeciePage>() })
}
