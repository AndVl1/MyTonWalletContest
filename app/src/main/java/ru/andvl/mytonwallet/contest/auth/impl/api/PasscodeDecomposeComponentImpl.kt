package ru.andvl.mytonwallet.contest.auth.impl.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import org.koin.androidx.compose.koinViewModel
import ru.andvl.mytonwallet.contest.auth.impl.model.AuthNavigationConfig
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeNavigationEvent
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeScreen
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeViewModel
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent

class PasscodeDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val navigation: StackNavigation<AuthNavigationConfig>
) : ScreenDecomposeComponent(componentContext) {

    @Composable
    override fun Render() {
        val viewModel: PasscodeViewModel = koinViewModel()

        val lifecycleOwner = LocalLifecycleOwner.current
        val navigationEvents = viewModel.navigationEvents

        LaunchedEffect(lifecycleOwner.lifecycle) {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                navigationEvents.collect { navigationEvent ->
                    when (navigationEvent) {
                        is PasscodeNavigationEvent.NavigateToWallet -> {
                            /*TODO*/
                        }
                    }
                }
            }
        }

        PasscodeScreen(
            state = viewModel.state.collectAsState().value,
            onAction = viewModel::obtainEvent
        )
    }
}