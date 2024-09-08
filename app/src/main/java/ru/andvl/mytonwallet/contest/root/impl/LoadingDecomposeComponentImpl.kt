package ru.andvl.mytonwallet.contest.root.impl

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent
import ru.andvl.mytonwallet.contest.ui.components.Loading
import ru.andvl.mytonwallet.contest.ui.components.LoadingStyle

class LoadingDecomposeComponentImpl(
    componentContext: ComponentContext
) : ScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render() {
        Loading(
            modifier = Modifier.fillMaxSize(),
            style = LoadingStyle.BOX
        )
    }
}