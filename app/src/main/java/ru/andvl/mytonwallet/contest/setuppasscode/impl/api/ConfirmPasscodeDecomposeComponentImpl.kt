package ru.andvl.mytonwallet.contest.setuppasscode.impl.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.replaceCurrent
import org.koin.compose.koinInject
import ru.andvl.mytonwallet.contest.arch.core.viewModelWithFactory
import ru.andvl.mytonwallet.contest.auth.impl.confirmpasscode.ConfirmPasscodeViewModel
import ru.andvl.mytonwallet.contest.auth.impl.confirmpasscode.CreateConfirmPasscodeScreen
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeLength
import ru.andvl.mytonwallet.contest.datastore.UserSettingsRepository
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent
import ru.andvl.mytonwallet.contest.setuppasscode.impl.model.SetUpPasscodeNavigationConfig

class ConfirmPasscodeDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val navigation: StackNavigation<SetUpPasscodeNavigationConfig>,
    private val correctPasscode: String,
    private val passcodeLength: PasscodeLength
) : ScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render() {
        val userSettingsRepository: UserSettingsRepository = koinInject()
        val viewModel = viewModelWithFactory(null) {
            ConfirmPasscodeViewModel(correctPasscode, passcodeLength, userSettingsRepository)
        }
        CreateConfirmPasscodeScreen(
            navigateBack = { navigation.pop() },
            navigateToBiometricLock = {
                navigation.replaceCurrent(
                    SetUpPasscodeNavigationConfig.BiometricLockScreen(
                        correctPasscode
                    )
                )
            },
            viewModel = viewModel
        )
    }
}
