package ru.andvl.mytonwallet.contest.setuppasscode.impl.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.replaceCurrent
import ru.andvl.mytonwallet.contest.arch.core.viewModelWithFactory
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.CreateSetPasscodeScreen
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.SetPasscodeViewModel
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent
import ru.andvl.mytonwallet.contest.setuppasscode.impl.model.SetUpPasscodeNavigationConfig

class SetPasscodeDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val navigation: StackNavigation<SetUpPasscodeNavigationConfig>
) : ScreenDecomposeComponent(componentContext) {

    @Composable
    override fun Render() {
        val viewModel = viewModelWithFactory(null) {
            SetPasscodeViewModel()
        }
        CreateSetPasscodeScreen(
            navigateBack = { navigation.pop() },
            navigateToConfirm = { passcode, length ->
                navigation.replaceCurrent(
                    SetUpPasscodeNavigationConfig.ConfirmPasscodeScreen(
                        correctPasscode = passcode,
                        passcodeLength = length
                    )
                )
            },
            viewModel
        )
    }
}