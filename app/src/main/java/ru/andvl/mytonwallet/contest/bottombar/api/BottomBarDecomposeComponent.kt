package ru.andvl.mytonwallet.contest.bottombar.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import ru.andvl.mytonwallet.contest.decompose.CompositeDecomposeComponent
import ru.andvl.mytonwallet.contest.decompose.DecomposeOnBackParameter

abstract class BottomBarDecomposeComponent<C : Any> : CompositeDecomposeComponent<C>() {
    @Composable
    abstract fun Render(modifier: Modifier)

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter
        ): BottomBarDecomposeComponent<*>
    }
}