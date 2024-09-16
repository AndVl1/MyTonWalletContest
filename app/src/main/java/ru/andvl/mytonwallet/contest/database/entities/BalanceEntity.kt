package ru.andvl.mytonwallet.contest.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigInteger

@Entity(tableName = "balances")
data class BalanceEntity(
    @PrimaryKey val accountId: String,
    val slug: String,
    val balance: BigInteger
)