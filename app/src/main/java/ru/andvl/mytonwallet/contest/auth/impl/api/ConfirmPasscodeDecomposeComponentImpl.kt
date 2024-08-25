package ru.andvl.mytonwallet.contest.auth.impl.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushToFront
import ru.andvl.mytonwallet.contest.auth.impl.confirmpasscode.CreateConfirmPasscodeScreen
import ru.andvl.mytonwallet.contest.auth.impl.model.AuthNavigationConfig
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeLength
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent

class ConfirmPasscodeDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val navigation: StackNavigation<AuthNavigationConfig>,
    private val correctPasscode: String,
    private val passcodeLength: PasscodeLength
) : ScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render() {
        CreateConfirmPasscodeScreen(
            correctPasscode = correctPasscode,
            passcodeLength = passcodeLength,
            navigateBack = { navigation.pop() },
            navigateToBiometricLock = {
                navigation.pushToFront(AuthNavigationConfig.BiometricLockScreen())
            }
        )
    }
}