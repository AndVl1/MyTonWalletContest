package ru.andvl.mytonwallet.contest.setuppasscode.api

import com.arkivanov.decompose.ComponentContext
import ru.andvl.mytonwallet.contest.decompose.CompositeDecomposeComponent
import ru.andvl.mytonwallet.contest.decompose.DecomposeOnBackParameter

abstract class SetUpPasscodeDecomposeComponent<C : Any> : CompositeDecomposeComponent<C>() {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter,
            navigateNext: () -> Unit,
        ): SetUpPasscodeDecomposeComponent<*>
    }
}