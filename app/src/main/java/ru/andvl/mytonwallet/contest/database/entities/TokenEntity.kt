package ru.andvl.mytonwallet.contest.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tokens")
data class TokenEntity(
    @PrimaryKey val slug: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "decimals") val decimals: Int,
    @ColumnInfo(name = "minterAddress") val minterAddress: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "isPopular") val isPopular: Boolean?,
    @ColumnInfo(name = "cmcSlug") val cmcSlug: String?,
    @ColumnInfo(name = "color") val color: String?,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "priceUsd") val priceUsd: Double,
    @ColumnInfo(name = "percentChange24h") val percentChange24h: Double
)