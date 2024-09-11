package ru.andvl.mytonwallet.contest.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.andvl.mytonwallet.contest.database.entities.StakingStateEntity

@Dao
interface StakingStateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateStakingState(stakingState: StakingStateEntity)

    @Query("SELECT * FROM staking_state WHERE accountId = :accountId")
    suspend fun getStakingStateByAccountId(accountId: String): StakingStateEntity?
}