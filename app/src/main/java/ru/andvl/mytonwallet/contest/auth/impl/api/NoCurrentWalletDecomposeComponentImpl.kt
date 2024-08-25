package ru.andvl.mytonwallet.contest.auth.impl.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushToFront
import ru.andvl.mytonwallet.contest.arch.core.viewModelWithFactory
import ru.andvl.mytonwallet.contest.auth.impl.model.AuthNavigationConfig
import ru.andvl.mytonwallet.contest.auth.impl.nowallet.CreateNoWalletScreen
import ru.andvl.mytonwallet.contest.auth.impl.nowallet.NoWalletScreen
import ru.andvl.mytonwallet.contest.auth.impl.nowallet.NoWalletViewModel
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent

class NoCurrentWalletDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val navigation: StackNavigation<AuthNavigationConfig>
) : ScreenDecomposeComponent(componentContext) {

    @Composable
    override fun Render() {
        val viewModel = viewModelWithFactory(null) {
            NoWalletViewModel()
        }
        CreateNoWalletScreen(
            navigateToCreate = {
                navigation.pushToFront(AuthNavigationConfig.WalletCreatedStartScreen())
            },
            navigateToImport = {
                navigation.pushToFront(AuthNavigationConfig.WalletImportScreen())
            },
            viewModel = viewModel
        )
    }
}