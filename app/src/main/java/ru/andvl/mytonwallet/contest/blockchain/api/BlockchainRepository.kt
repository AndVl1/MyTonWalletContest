package ru.andvl.mytonwallet.contest.blockchain.api

import ru.andvl.mytonwallet.contest.bottombar.impl.model.AssetToken

interface BlockchainRepository {
    suspend fun getMnemonicWordList(): List<String>
    suspend fun generateMnemonic(): List<String>
    fun getMnemonicCheckIndexes(): List<Int>
    suspend fun validateMnemonic(mnemonic: List<String>): Boolean
    suspend fun checkApiAvailability(): Boolean
    fun updateCurrentAccountId(id: String)
    suspend fun createAccount(
        mnemonic: List<String>,
        passcode: String,
        isImport: Boolean = false
    )

    suspend fun getCurrentAccountTokenBalances(): List<AssetToken>
}
