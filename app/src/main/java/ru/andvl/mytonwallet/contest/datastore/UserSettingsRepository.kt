package ru.andvl.mytonwallet.contest.datastore

import kotlinx.coroutines.flow.Flow

interface UserSettingsRepository {
    suspend fun updateWalletAddress(address: String)
    suspend fun updateWalletAccountId(accountId: String)
    suspend fun updateAuthByFingerPrint(isByFingerprint: Boolean)
    suspend fun updatePasscode(passcode: String)

    fun getWalletAddress(): Flow<String>
    fun getWalletAccountId(): Flow<String>
    fun getAuthByFingerPrint(): Flow<Boolean>
    fun getPasscode(): Flow<String>
}