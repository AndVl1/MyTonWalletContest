package ru.andvl.mytonwallet.contest.di

import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import org.koin.dsl.module
import ru.andvl.mytonwallet.contest.MainActivity
import ru.andvl.mytonwallet.contest.auth.api.AuthDecomposeComponent
import ru.andvl.mytonwallet.contest.auth.impl.api.AuthDecomposeComponentImpl
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository
import ru.andvl.mytonwallet.contest.blockchain.impl.BlockchainRepositoryWebViewImpl
import ru.andvl.mytonwallet.contest.blockchain.util.WebViewHolder
import ru.andvl.mytonwallet.contest.bottombar.api.BottomBarDecomposeComponent
import ru.andvl.mytonwallet.contest.bottombar.impl.api.BottomBarDecomposeComponentImpl
import ru.andvl.mytonwallet.contest.database.MyTonWalletDatabase
import ru.andvl.mytonwallet.contest.database.daos.AccountAddressColorsDao
import ru.andvl.mytonwallet.contest.database.daos.BalanceDao
import ru.andvl.mytonwallet.contest.database.daos.StakingStateDao
import ru.andvl.mytonwallet.contest.database.daos.SwapTokenDao
import ru.andvl.mytonwallet.contest.database.daos.TokenDao
import ru.andvl.mytonwallet.contest.datastore.UserSettingsRepository
import ru.andvl.mytonwallet.contest.datastore.UserSettingsRepositoryImpl
import ru.andvl.mytonwallet.contest.root.api.RootDecomposeComponent
import ru.andvl.mytonwallet.contest.root.impl.RootDecomposeComponentImpl
import ru.andvl.mytonwallet.contest.setuppasscode.api.SetUpPasscodeDecomposeComponent
import ru.andvl.mytonwallet.contest.setuppasscode.impl.api.SetUpPasscodeDecomposeComponentImpl
import ru.andvl.mytonwallet.contest.utils.BiometricPromptManager

val appModule = module {
    single { WebViewHolder(get()) }
    single { BiometricPromptManager(get()) }
    single<AppCompatActivity> {
        MainActivity.getInstance() as MainActivity
    }
    single<AuthDecomposeComponent.Factory> {
        AuthDecomposeComponentImpl.Factory(get())
    }
    single<RootDecomposeComponent.Factory> {
        RootDecomposeComponentImpl.Factory(get(), get(), get())
    }
    single<SetUpPasscodeDecomposeComponent.Factory> {
        SetUpPasscodeDecomposeComponentImpl.Factory()
    }
    single<BottomBarDecomposeComponent.Factory> {
        BottomBarDecomposeComponentImpl.Factory()
    }
    single<BlockchainRepository> {
        BlockchainRepositoryWebViewImpl(get(), get(), get(), get(), get(), get(), get())
    }
    single<UserSettingsRepository> {
        UserSettingsRepositoryImpl(get())
    }
    single<MyTonWalletDatabase> {
        Room.databaseBuilder(
            get(),
            MyTonWalletDatabase::class.java,
            "mytonwallet_database"
        ).fallbackToDestructiveMigration()
            .build()
    }
    single<BalanceDao> {
        get<MyTonWalletDatabase>().balanceDao()
    }
    single<TokenDao> {
        get<MyTonWalletDatabase>().tokenDao()
    }
    single<SwapTokenDao> {
        get<MyTonWalletDatabase>().swapTokenDao()
    }
    single<StakingStateDao> {
        get<MyTonWalletDatabase>().stakingStateDao()
    }
    single<AccountAddressColorsDao> {
        get<MyTonWalletDatabase>().walletAddressColorsDao()
    }
}
