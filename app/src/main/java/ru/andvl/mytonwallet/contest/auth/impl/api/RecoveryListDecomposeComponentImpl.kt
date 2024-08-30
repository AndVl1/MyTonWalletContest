package ru.andvl.mytonwallet.contest.auth.impl.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushToFront
import ru.andvl.mytonwallet.contest.arch.core.viewModelWithFactory
import ru.andvl.mytonwallet.contest.auth.impl.model.AuthNavigationConfig
import ru.andvl.mytonwallet.contest.auth.impl.recoverylist.CreateRecoveryListScreen
import ru.andvl.mytonwallet.contest.auth.impl.recoverylist.RecoveryListViewModel
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent

class RecoveryListDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val navigation: StackNavigation<AuthNavigationConfig>
) : ScreenDecomposeComponent(componentContext) {

    @Composable
    override fun Render() {
        val viewModel = viewModelWithFactory(null) {
            RecoveryListViewModel()
        }
        CreateRecoveryListScreen(
            navigateToRecoveryTest = {
                navigation.pushToFront(AuthNavigationConfig.RecoveryTestScreen())
            },
            viewModel = viewModel
        )
    }
}