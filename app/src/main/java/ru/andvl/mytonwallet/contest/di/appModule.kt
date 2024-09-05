package ru.andvl.mytonwallet.contest.di

import org.koin.dsl.module
import ru.andvl.mytonwallet.contest.auth.api.AuthDecomposeComponent
import ru.andvl.mytonwallet.contest.auth.impl.api.AuthDecomposeComponentImpl
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository
import ru.andvl.mytonwallet.contest.blockchain.impl.BlockchainRepositoryWebViewImpl
import ru.andvl.mytonwallet.contest.blockchain.util.WebViewHolder
import ru.andvl.mytonwallet.contest.bottombar.api.BottomBarDecomposeComponent
import ru.andvl.mytonwallet.contest.bottombar.impl.api.BottomBarDecomposeComponentImpl
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
    single<BottomBarDecomposeComponent.Factory> {
        BottomBarDecomposeComponentImpl.Factory()
    }
    single<BlockchainRepository> { BlockchainRepositoryWebViewImpl(get()) }
}
