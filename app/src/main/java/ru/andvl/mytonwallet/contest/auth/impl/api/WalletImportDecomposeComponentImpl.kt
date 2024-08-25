package ru.andvl.mytonwallet.contest.auth.impl.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import ru.andvl.mytonwallet.contest.arch.core.viewModelWithFactory
import ru.andvl.mytonwallet.contest.auth.impl.walletimport.WalletImportScreen
import ru.andvl.mytonwallet.contest.auth.impl.walletimport.WalletImportViewModel
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent

class WalletImportDecomposeComponentImpl(
    componentContext: ComponentContext,
): ScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render() {
        val viewModel = viewModelWithFactory(null) {
            WalletImportViewModel()
        }
        WalletImportScreen(viewModel = viewModel)
    }
}
