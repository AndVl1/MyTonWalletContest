package ru.andvl.mytonwallet.contest.di

import android.webkit.WebView
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.andvl.mytonwallet.contest.auth.api.AuthDecomposeComponent
import ru.andvl.mytonwallet.contest.auth.impl.api.AuthDecomposeComponentImpl
import ru.andvl.mytonwallet.contest.auth.impl.nowallet.NoWalletViewModel
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeViewModel
import ru.andvl.mytonwallet.contest.auth.impl.walletimport.WalletImportViewModel
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository
import ru.andvl.mytonwallet.contest.blockchain.impl.BlockchainRepositoryWebViewImpl
import ru.andvl.mytonwallet.contest.blockchain.util.WebViewHolder
import ru.andvl.mytonwallet.contest.root.api.RootDecomposeComponent
import ru.andvl.mytonwallet.contest.root.impl.RootDecomposeComponentImpl

val appModule = module {
    single { WebViewHolder(get()) }
    single<AuthDecomposeComponent.Factory> {
        AuthDecomposeComponentImpl.Factory()
    }
    single<RootDecomposeComponent.Factory> {
        RootDecomposeComponentImpl.Factory(get())
    }
    single<BlockchainRepository> { BlockchainRepositoryWebViewImpl(get()) }

    viewModel { NoWalletViewModel() }
    viewModel { WalletImportViewModel() }
    viewModel { PasscodeViewModel() }
}
