package ru.andvl.mytonwallet.contest.bottombar.impl.api

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import ru.andvl.mytonwallet.contest.bottombar.impl.model.BottomBarConfig
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent

class AssetsDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val navigation: StackNavigation<BottomBarConfig>
) : ScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render() {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "ASSETS")
        }
    }
}