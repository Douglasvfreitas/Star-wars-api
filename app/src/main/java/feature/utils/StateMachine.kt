package feature.utils

sealed class StateMachine<out T> {

    object Start : StateMachine<Nothing>()

    data class Success<out T>(val value: T) : StateMachine<T>()

    data class Failure(val exception: Throwable) : StateMachine<Nothing>()

    object Finish : StateMachine<Nothing>()
}