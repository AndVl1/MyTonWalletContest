package ru.andvl.mytonwallet.contest.arch

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<A: Action, S: State> : ViewModel() {

    abstract val state: StateFlow<S>

    abstract fun obtainEvent(event: A)
}