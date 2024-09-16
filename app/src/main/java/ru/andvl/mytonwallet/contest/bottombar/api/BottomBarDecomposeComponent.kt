package ru.andvl.mytonwallet.contest.bottombar.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import ru.andvl.mytonwallet.contest.decompose.CompositeDecomposeComponent
import ru.andvl.mytonwallet.contest.decompose.DecomposeOnBackParameter

abstract class BottomBarDecomposeComponent<C : Any> : CompositeDecomposeComponent<C>() {
    @Composable
    abstract override fun Render()

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter
        ): BottomBarDecomposeComponent<*>
    }
}