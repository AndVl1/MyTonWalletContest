package ru.andvl.mytonwallet.contest.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.andvl.mytonwallet.contest.database.entities.SwapTokenEntity

@Dao
interface SwapTokenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTokens(swapTokens: List<SwapTokenEntity>)

    @Query("SELECT * FROM swap_tokens WHERE slug = :slug")
    fun getTokenBySlug(slug: String): Flow<SwapTokenEntity>

    @Query("SELECT * FROM swap_tokens")
    fun getAllTokens(): Flow<List<SwapTokenEntity>>
}