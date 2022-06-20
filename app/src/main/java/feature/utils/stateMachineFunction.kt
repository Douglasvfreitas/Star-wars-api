package feature.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

fun <T> stateMachineFunction(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    action: suspend () -> T
): Flow<StateMachine<T>> =
    flow<StateMachine<T>> { emit(StateMachine.Success(action())) }
        .catch { exception -> emit(StateMachine.Failure(exception)) }
        .onStart { emit(StateMachine.Start) }
        .onCompletion { emit(StateMachine.Finish) }
        .flowOn(dispatcher)