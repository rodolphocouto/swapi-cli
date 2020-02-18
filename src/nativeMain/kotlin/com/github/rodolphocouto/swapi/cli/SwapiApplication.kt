package com.github.rodolphocouto.swapi.cli

import com.github.rodolphocouto.swapi.cli.Operation.COUNT
import com.github.rodolphocouto.swapi.cli.Operation.GET
import com.github.rodolphocouto.swapi.cli.Operation.LIST
import com.github.rodolphocouto.swapi.cli.Operation.SEARCH
import com.github.rodolphocouto.swapi.cli.format.format
import com.github.rodolphocouto.swapi.client.Resource
import com.github.rodolphocouto.swapi.client.SwapiClient
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.ArgType.Choice
import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand
import kotlinx.cli.optional
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.UnstableDefault

enum class Operation { COUNT, LIST, SEARCH, GET }

@ExperimentalCli
@UnstableDefault
class ResourceCommand<T>(
    name: String,
    private val resource: Resource<T>,
    private val format: (T?) -> String
) : Subcommand(name) {

    private val op by argument(Choice(enumValues<Operation>().map { it.name.toLowerCase() }), description = "operation")
    private val param by argument(ArgType.String, description = "parameter - only for search and get").optional()

    override fun execute() = runBlocking {
        val output = when (enumValueOf<Operation>(op.toUpperCase())) {
            COUNT -> listOf(resource.count().toString())
            LIST -> resource.list().map { format(it) }
            SEARCH -> resource.search(param ?: "").map { format(it) }
            GET -> listOf(format(resource.get(param?.toInt() ?: 0)))
        }

        println(output.joinToString(separator = "\n\n"))
    }
}

@ExperimentalCli
@UnstableDefault
fun main(args: Array<String>) {
    val client = SwapiClient()
    val parser = ArgParser("swapi")

    parser.subcommands(
        ResourceCommand("people", client.people) { it.format() },
        ResourceCommand("films", client.films) { it.format() },
        ResourceCommand("starships", client.starships) { it.format() },
        ResourceCommand("vehicles", client.vehicles) { it.format() },
        ResourceCommand("species", client.species) { it.format() },
        ResourceCommand("planets", client.planets) { it.format() }
    )

    parser.parse(args)
}
