package ru.andvl.mytonwallet.contest.auth.impl.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushToFront
import ru.andvl.mytonwallet.contest.auth.impl.model.AuthNavigationConfig
import ru.andvl.mytonwallet.contest.auth.impl.nowallet.NoWalletScreen
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent

class NoCurrentWalletDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val navigation: StackNavigation<AuthNavigationConfig>
): ScreenDecomposeComponent(componentContext) {

    @Composable
    override fun Render() {
        NoWalletScreen(
            onCreateClicked = {
                navigation.pushToFront(AuthNavigationConfig.WalletCreatedStartScreen())
            },
            onImportClicked = {
                navigation.pushToFront(AuthNavigationConfig.WalletImportScreen())
            },
            modifier = Modifier
        )
    }
}
