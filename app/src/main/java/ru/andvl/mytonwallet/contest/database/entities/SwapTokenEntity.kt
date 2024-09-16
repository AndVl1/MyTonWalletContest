package ru.andvl.mytonwallet.contest.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "swap_tokens")
data class SwapTokenEntity(
    @PrimaryKey val slug: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "blockchain") val blockchain: String,
    @ColumnInfo(name = "decimals") val decimals: Int,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "isPopular") val isPopular: Boolean,
    @ColumnInfo(name = "color") val color: String?,
    @ColumnInfo(name = "price") val price: Float,
    @ColumnInfo(name = "priceUsd") val priceUsd: Float,
    @ColumnInfo(name = "contract") val contract: String?,
//    @ColumnInfo(name = "keywords") val keywords: List<String>?,
)