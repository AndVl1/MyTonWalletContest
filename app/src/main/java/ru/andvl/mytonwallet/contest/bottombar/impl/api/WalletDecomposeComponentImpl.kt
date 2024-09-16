package ru.andvl.mytonwallet.contest.bottombar.impl.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import org.koin.compose.koinInject
import ru.andvl.mytonwallet.contest.arch.core.viewModelWithFactory
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository
import ru.andvl.mytonwallet.contest.bottombar.impl.model.BottomBarConfig
import ru.andvl.mytonwallet.contest.bottombar.impl.wallet.CreateWalletScreen
import ru.andvl.mytonwallet.contest.bottombar.impl.wallet.WalletViewModel
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent

class WalletDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val navigation: StackNavigation<BottomBarConfig>
) : ScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render() {
        val blockchainRepository: BlockchainRepository = koinInject()
        val viewModel = viewModelWithFactory(null) {
            WalletViewModel(blockchainRepository)
        }
        CreateWalletScreen(
            navigateToSend = { /*TODO*/ },
            viewModel = viewModel
        )
    }
}