package ru.andvl.mytonwallet.contest.auth.api

import com.arkivanov.decompose.ComponentContext
import ru.andvl.mytonwallet.contest.decompose.CompositeDecomposeComponent
import ru.andvl.mytonwallet.contest.decompose.DecomposeOnBackParameter

abstract class AuthDecomposeComponent<C : Any> : CompositeDecomposeComponent<C>() {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            launchType: AuthLaunchType,
            onBack: DecomposeOnBackParameter,
            navigateToMain: () -> Unit
        ): AuthDecomposeComponent<*>
    }
}
