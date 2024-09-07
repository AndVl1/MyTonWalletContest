package ru.andvl.mytonwallet.contest.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.andvl.mytonwallet.contest.database.entities.TokenEntity

@Dao
interface TokenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTokens(tokens: List<TokenEntity>)

    @Query("SELECT * FROM tokens WHERE slug = :slug")
    fun getTokenBySlug(slug: String): Flow<TokenEntity>

    @Query("SELECT * FROM tokens")
    fun getAllTokens(): Flow<List<TokenEntity>>
}