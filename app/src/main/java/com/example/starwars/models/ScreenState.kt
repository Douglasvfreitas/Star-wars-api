package com.example.starwars.models



sealed class ScreenState<out T> {
    object Loading : ScreenState<Nothing>()
    data class Result<out T>(val value: T) : ScreenState<T>()
    data class Error(val value: Throwable) : ScreenState<Nothing>()
}




