package ru.andvl.mytonwallet.contest.auth.impl.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushToFront
import ru.andvl.mytonwallet.contest.auth.impl.biometriclock.CreateBiometricLockScreen
import ru.andvl.mytonwallet.contest.auth.impl.model.AuthNavigationConfig
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent

class BiometricLockDecomposeComponent(
    componentContext: ComponentContext,
    private val navigation: StackNavigation<AuthNavigationConfig>
) : ScreenDecomposeComponent(componentContext) {

    @Composable
    override fun Render() {
        CreateBiometricLockScreen(
            navigateToRecoveryList = {
                navigation.pushToFront(AuthNavigationConfig.RecoveryListScreen())
            }
        )
    }
}