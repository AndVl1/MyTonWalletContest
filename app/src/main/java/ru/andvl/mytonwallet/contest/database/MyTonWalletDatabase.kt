package ru.andvl.mytonwallet.contest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.andvl.mytonwallet.contest.database.converters.Converters
import ru.andvl.mytonwallet.contest.database.daos.AccountAddressColorsDao
import ru.andvl.mytonwallet.contest.database.daos.BalanceDao
import ru.andvl.mytonwallet.contest.database.daos.StakingStateDao
import ru.andvl.mytonwallet.contest.database.daos.SwapTokenDao
import ru.andvl.mytonwallet.contest.database.daos.TokenDao
import ru.andvl.mytonwallet.contest.database.entities.AccountAddressColorsEntity
import ru.andvl.mytonwallet.contest.database.entities.BalanceEntity
import ru.andvl.mytonwallet.contest.database.entities.StakingStateEntity
import ru.andvl.mytonwallet.contest.database.entities.SwapTokenEntity
import ru.andvl.mytonwallet.contest.database.entities.TokenEntity

@Database(
    entities = [
        BalanceEntity::class,
        TokenEntity::class,
        SwapTokenEntity::class,
        StakingStateEntity::class,
        AccountAddressColorsEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MyTonWalletDatabase : RoomDatabase() {
    abstract fun balanceDao(): BalanceDao
    abstract fun tokenDao(): TokenDao
    abstract fun swapTokenDao(): SwapTokenDao
    abstract fun stakingStateDao(): StakingStateDao
    abstract fun walletAddressColorsDao(): AccountAddressColorsDao
}