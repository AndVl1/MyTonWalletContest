package ru.andvl.mytonwallet.contest.arch

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import ru.andvl.mytonwallet.contest.arch.core.DecomposeViewModel

abstract class BaseViewModel<A: Action, S: State> : DecomposeViewModel() {

    abstract val state: StateFlow<S>

    abstract fun obtainEvent(event: A)
}
