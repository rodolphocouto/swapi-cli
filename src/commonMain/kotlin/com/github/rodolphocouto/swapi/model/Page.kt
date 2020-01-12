package com.github.rodolphocouto.swapi.model

interface Page<T> {

    val count: Int
    val previous: String?
    val next: String?
    val results: Array<T>
}
