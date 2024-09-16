package ru.andvl.mytonwallet.contest.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_address_colors")
data class AccountAddressColorsEntity(
    @PrimaryKey val address: String,
    val color: Int
)