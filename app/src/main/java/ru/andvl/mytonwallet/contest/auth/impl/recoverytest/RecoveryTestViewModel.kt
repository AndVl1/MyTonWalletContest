package ru.andvl.mytonwallet.contest.auth.impl.recoverytest

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.andvl.mytonwallet.contest.arch.BaseViewModel

class RecoveryTestViewModel(
    recoveryWords: List<String>
) : BaseViewModel<RecoveryTestAction, RecoveryTestState>() {
    private val _state = MutableStateFlow(RecoveryTestState(recoveryWords))

    override val state: StateFlow<RecoveryTestState> = _state.asStateFlow()

    override fun obtainEvent(event: RecoveryTestAction) {
        when (event) {
            is RecoveryTestAction.WordEntered -> updateWord(event.index, event.word)
            RecoveryTestAction.ContinueClicked -> verifyWords()
        }
    }

    private fun updateWord(index: Int, word: String) {
        TODO()
//        _state.update {
//            it.copy(
//                recoveryWords = it.recoveryWords.
//            )
//        }
    }

    private fun verifyWords() {
        // TODO implement verification logic
    }
}
