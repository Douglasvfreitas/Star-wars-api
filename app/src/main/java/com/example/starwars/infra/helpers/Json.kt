package com.example.starwars.infra.helpers

import kotlinx.serialization.json.Json

object Json {
    val parser by lazy {
        Json {
            allowSpecialFloatingPointValues = true
            encodeDefaults = true
            ignoreUnknownKeys = true
            isLenient = true
            useArrayPolymorphism = true
        }
    }
}