package ru.andvl.mytonwallet.contest.auth.impl.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushToFront
import ru.andvl.mytonwallet.contest.auth.impl.model.AuthNavigationConfig
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.CreateSetPasscodeScreen
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent

class SetPasscodeDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val navigation: StackNavigation<AuthNavigationConfig>
) : ScreenDecomposeComponent(componentContext) {

    @Composable
    override fun Render() {
        CreateSetPasscodeScreen(
            navigateBack = { navigation.pop() },
            navigateToBiometricLock = {
                navigation.pushToFront(AuthNavigationConfig.BiometricLockScreen())
            }
        )
    }
}