package ru.andvl.mytonwallet.contest.auth.impl.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import ru.andvl.mytonwallet.contest.arch.core.viewModelWithFactory
import ru.andvl.mytonwallet.contest.auth.impl.model.AuthNavigationConfig
import ru.andvl.mytonwallet.contest.auth.impl.recoverytest.CreateRecoveryTestScreen
import ru.andvl.mytonwallet.contest.auth.impl.recoverytest.RecoveryTestViewModel
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent

class RecoveryTestDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val navigation: StackNavigation<AuthNavigationConfig>,
    private val recoveryWords: List<String>
) : ScreenDecomposeComponent(componentContext) {

    @Composable
    override fun Render() {
        val viewModel = viewModelWithFactory(null) {
            RecoveryTestViewModel(recoveryWords)
        }
        CreateRecoveryTestScreen(
            navigateBack = { navigation.pop() },
            navigateToHome = {
                /*TODO*/
            },
            viewModel = viewModel
        )
    }
}