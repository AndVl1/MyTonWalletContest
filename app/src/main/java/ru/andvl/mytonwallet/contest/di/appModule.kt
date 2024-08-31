package ru.andvl.mytonwallet.contest.di

import org.koin.dsl.module
import ru.andvl.mytonwallet.contest.auth.api.AuthDecomposeComponent
import ru.andvl.mytonwallet.contest.auth.impl.api.AuthDecomposeComponentImpl
import ru.andvl.mytonwallet.contest.root.api.RootDecomposeComponent
import ru.andvl.mytonwallet.contest.root.impl.RootDecomposeComponentImpl

val appModule = module {
    single<AuthDecomposeComponent.Factory> {
        AuthDecomposeComponentImpl.Factory()
    }
    single<RootDecomposeComponent.Factory> {
        RootDecomposeComponentImpl.Factory(get())
    }
}
