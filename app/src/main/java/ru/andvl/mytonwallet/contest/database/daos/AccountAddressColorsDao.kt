package ru.andvl.mytonwallet.contest.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ru.andvl.mytonwallet.contest.database.entities.AccountAddressColorsEntity

@Dao
interface AccountAddressColorsDao {
    @Upsert
    suspend fun updateAccountColor(accountAddressColors: AccountAddressColorsEntity)

    @Query("SELECT color FROM account_address_colors WHERE address = :accountAddress LIMIT 1")
    suspend fun getColorByAccountAddress(accountAddress: String): Int?

    @Query("DELETE FROM account_address_colors WHERE address = :accountAddress")
    suspend fun deleteWalletColor(accountAddress: String)

    @Query("DELETE FROM account_address_colors")
    suspend fun deleteAllWalletColors()
}