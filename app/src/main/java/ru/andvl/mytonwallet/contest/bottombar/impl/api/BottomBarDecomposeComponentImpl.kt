package ru.andvl.mytonwallet.contest.bottombar.impl.api

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import ru.andvl.mytonwallet.contest.bottombar.api.BottomBarDecomposeComponent
import ru.andvl.mytonwallet.contest.bottombar.impl.model.BottomBarConfig
import ru.andvl.mytonwallet.contest.bottombar.impl.model.BottomBarEnum
import ru.andvl.mytonwallet.contest.bottombar.impl.ui.BottomBarScreen
import ru.andvl.mytonwallet.contest.decompose.DecomposeComponent
import ru.andvl.mytonwallet.contest.decompose.DecomposeOnBackParameter

class BottomBarDecomposeComponentImpl(
    componentContext: ComponentContext,
) : BottomBarDecomposeComponent<BottomBarConfig>(), ComponentContext by componentContext {
    override val stack: Value<ChildStack<BottomBarConfig, DecomposeComponent>> =
        childStack(
            source = navigation,
            serializer = BottomBarConfig.serializer(),
            initialConfiguration = BottomBarConfig.WalletScreen,
            childFactory = ::child
        )

    @Composable
    @Suppress("NonSkippableComposable")
    override fun Render(modifier: Modifier) {
        val childStack by stack.subscribeAsState()

        BottomBarScreen(
            childStack = childStack,
            modifier = Modifier.fillMaxSize(),
            onSelect = {
                when (it) {
                    BottomBarEnum.WALLET -> navigation.bringToFront(BottomBarConfig.WalletScreen)
                    BottomBarEnum.ASSETS -> navigation.bringToFront(BottomBarConfig.AssetsScreen)
                    BottomBarEnum.BROWSER -> navigation.bringToFront(BottomBarConfig.BrowserScreen)
                    BottomBarEnum.SETTINGS -> navigation.bringToFront(BottomBarConfig.SettingsScreen)
                }
            }
        )
    }

    private fun child(
        config: BottomBarConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        is BottomBarConfig.WalletScreen -> WalletDecomposeComponentImpl(
            componentContext,
            navigation
        )

        is BottomBarConfig.AssetsScreen -> TODO()
        is BottomBarConfig.BrowserScreen -> TODO()
        is BottomBarConfig.SettingsScreen -> TODO()
    }

    class Factory : BottomBarDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter
        ): BottomBarDecomposeComponent<*> = BottomBarDecomposeComponentImpl(componentContext)
    }
}
