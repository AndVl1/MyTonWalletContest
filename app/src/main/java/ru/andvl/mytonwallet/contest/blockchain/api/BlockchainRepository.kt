package ru.andvl.mytonwallet.contest.blockchain.api

import kotlinx.coroutines.flow.Flow
import ru.andvl.mytonwallet.contest.bottombar.impl.model.AssetToken
import ru.andvl.mytonwallet.contest.database.entities.BalanceEntity
import java.math.BigInteger

interface BlockchainRepository {
    suspend fun getMnemonicWordList(): List<String>
    suspend fun generateMnemonic(): List<String>
    fun getMnemonicCheckIndexes(): List<Int>
    suspend fun validateMnemonic(mnemonic: List<String>): Boolean
    suspend fun checkApiAvailability(): Boolean
    suspend fun updateCurrentAccountId(id: String)
    suspend fun createAccount(
        mnemonic: List<String>,
        passcode: String,
        isImport: Boolean = false
    )

    suspend fun getCurrentAccountWalletBalance(): BigInteger
    suspend fun getCurrentAccountBalances(): Flow<List<BalanceEntity>>
    suspend fun getCurrentAccountAssetTokens(): Flow<List<AssetToken>>
}
