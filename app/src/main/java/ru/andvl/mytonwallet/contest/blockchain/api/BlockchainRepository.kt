package ru.andvl.mytonwallet.contest.blockchain.api

interface BlockchainRepository {
    suspend fun getMnemonicWordList(): List<String>
}
