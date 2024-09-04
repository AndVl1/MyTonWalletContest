package ru.andvl.mytonwallet.contest.blockchain.api

interface BlockchainRepository {
    suspend fun getMnemonicWordList(): List<String>
    suspend fun generateMnemonic(): List<String>
    fun getMnemonicCheckIndexes(): List<Int>
    suspend fun validateMnemonic(mnemonic: List<String>): Boolean
}
