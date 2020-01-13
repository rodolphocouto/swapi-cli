package com.github.rodolphocouto.swapi.cli

import com.github.rodolphocouto.swapi.cli.Operation.COUNT
import com.github.rodolphocouto.swapi.cli.Operation.GET
import com.github.rodolphocouto.swapi.cli.Operation.LIST
import com.github.rodolphocouto.swapi.cli.Operation.SEARCH
import com.github.rodolphocouto.swapi.cli.Resource.FILMS
import com.github.rodolphocouto.swapi.cli.Resource.PEOPLE
import com.github.rodolphocouto.swapi.cli.Resource.STARSHIPS
import com.github.rodolphocouto.swapi.cli.format.format
import com.github.rodolphocouto.swapi.cli.format.formatToString
import com.github.rodolphocouto.swapi.client.SwapiClient
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.UnstableDefault

enum class Resource { PEOPLE, FILMS, STARSHIPS }
enum class Operation(val hasParam: Boolean = false) { COUNT, LIST, SEARCH(true), GET(true) }
data class Command(val resource: Resource, val operation: Operation, val param: String?)

@UnstableDefault
fun main(args: Array<String>) = runBlocking {
    val command = parseArgs(args)
    val swapi = SwapiClient()

    val output = when (command.resource) {
        PEOPLE -> people(command, swapi)
        FILMS -> films(command, swapi)
        STARSHIPS -> starships(command, swapi)
    }

    println(output)
}

@UnstableDefault
private suspend fun people(command: Command, swapi: SwapiClient) =
    when (command.operation) {
        COUNT -> swapi.people.count().toString()
        LIST -> swapi.people.list().formatToString()
        SEARCH -> swapi.people.search(command.param!!).formatToString()
        GET -> swapi.people.get(command.param!!.toInt()).format()
    }

@UnstableDefault
private suspend fun films(command: Command, swapi: SwapiClient) =
    when (command.operation) {
        COUNT -> swapi.films.count().toString()
        LIST -> swapi.films.list().formatToString()
        SEARCH -> swapi.films.search(command.param!!).formatToString()
        GET -> swapi.films.get(command.param!!.toInt()).format()
    }

@UnstableDefault
private suspend fun starships(command: Command, swapi: SwapiClient) =
    when (command.operation) {
        COUNT -> swapi.starships.count().toString()
        LIST -> swapi.starships.list().formatToString()
        SEARCH -> swapi.starships.search(command.param!!).formatToString()
        GET -> swapi.starships.get(command.param!!.toInt()).format()
    }

private fun parseArgs(args: Array<String>): Command {
    if (args.size !in 2..3) {
        throw IllegalArgumentException("Invalid arguments")
    }

    val resource = enumValues<Resource>()
        .firstOrNull { it.toString().toLowerCase() == args[0] }
        ?: throw IllegalArgumentException("Invalid resource")

    val operation = enumValues<Operation>()
        .firstOrNull { it.toString().toLowerCase() == args[1] }
        ?: throw IllegalArgumentException("Invalid operation")

    if (operation.hasParam && args.size != 3) {
        throw IllegalArgumentException("Invalid arguments")
    }

    val param = if (operation.hasParam) args[2] else null

    return Command(resource, operation, param)
}
