package ru.andvl.mytonwallet.contest.blockchain.api

interface BlockchainRepository {
    suspend fun initApi()
    suspend fun getMnemonicWordList(): Result<String>
    suspend fun createWallet(): Result<String>
}
