package ru.andvl.mytonwallet.contest.setuppasscode.impl.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import ru.andvl.mytonwallet.contest.arch.core.viewModelWithFactory
import ru.andvl.mytonwallet.contest.auth.impl.biometriclock.BiometricLockViewModel
import ru.andvl.mytonwallet.contest.auth.impl.biometriclock.CreateBiometricLockScreen
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent

class BiometricLockDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val navigateNext: () -> Unit
) : ScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render() {
        val viewModel = viewModelWithFactory(null) {
            BiometricLockViewModel()
        }
        CreateBiometricLockScreen(
            navigateToRecoveryList = navigateNext,
            viewModel = viewModel
        )
    }
}