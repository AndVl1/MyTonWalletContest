package ru.andvl.mytonwallet.contest.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.andvl.mytonwallet.contest.database.entities.BalanceEntity

@Dao
interface BalanceDao {
    @Query("SELECT * FROM balances WHERE accountId = :accountId")
    fun getBalancesForAccount(accountId: String): Flow<List<BalanceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBalances(balances: List<BalanceEntity>)

    @Update
    suspend fun updateBalance(balance: BalanceEntity)
}