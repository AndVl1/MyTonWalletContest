package ru.andvl.mytonwallet.contest.auth.impl.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import ru.andvl.mytonwallet.contest.auth.impl.model.AuthNavigationConfig
import ru.andvl.mytonwallet.contest.auth.impl.passcode.CreatePasscodeScreen
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent

class SetPasscodeDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val navigation: StackNavigation<AuthNavigationConfig>
) : ScreenDecomposeComponent(componentContext) {

    @Composable
    override fun Render() {
        CreatePasscodeScreen(
            navigateToWallet = { /*TODO*/ }
        )
    }
}