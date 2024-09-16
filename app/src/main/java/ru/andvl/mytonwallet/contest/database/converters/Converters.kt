package ru.andvl.mytonwallet.contest.database.converters

import androidx.room.TypeConverter
import java.math.BigInteger

class Converters {
    @TypeConverter
    fun fromBigInteger(value: BigInteger?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toBigInteger(value: String?): BigInteger? {
        return value?.let { BigInteger(it) }
    }
}