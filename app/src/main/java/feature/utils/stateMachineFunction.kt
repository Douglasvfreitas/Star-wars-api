package feature.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.flowOn

fun <T> stateMachineFunction(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    action: suspend () -> T
): Flow<StateMachine<T>> =
    flow<StateMachine<T>> { emit(StateMachine.Success(action())) }
        .catch { exception -> emit(StateMachine.Failure(exception)) }
        .onStart { emit(StateMachine.Start) }
        .onCompletion { emit(StateMachine.Finish) }
        .flowOn(dispatcher)