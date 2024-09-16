package ru.andvl.mytonwallet.contest.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.ApiStakingType

@Entity(tableName = "staking_state")
data class StakingStateEntity(
    @PrimaryKey val accountId: String,
    val type: ApiStakingType,
    val balance: String,
    val isUnstakeRequested: Boolean,
    val unstakeRequestedAmount: String?,
    val apy: Float,
    val start: Long,
    val end: Long,
    val totalProfit: String
)