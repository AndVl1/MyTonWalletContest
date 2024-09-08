package ru.andvl.mytonwallet.contest.auth.impl.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import org.koin.compose.koinInject
import ru.andvl.mytonwallet.contest.arch.core.viewModelWithFactory
import ru.andvl.mytonwallet.contest.auth.impl.passcode.CreatePasscodeScreen
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeViewModel
import ru.andvl.mytonwallet.contest.datastore.UserSettingsRepository
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent

class PasscodeDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val navigateToMain: () -> Unit,
) : ScreenDecomposeComponent(componentContext) {

    @Composable
    override fun Render() {
        val userSettingsRepository: UserSettingsRepository = koinInject()
        val viewModel = viewModelWithFactory(null) {
            PasscodeViewModel(userSettingsRepository)
        }
        CreatePasscodeScreen(
            navigateToMain = navigateToMain,
            viewModel = viewModel
        )
    }
}