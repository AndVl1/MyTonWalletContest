package ru.andvl.mytonwallet.contest.blockchain.api

interface BlockchainRepository {
    suspend fun initApi()
    suspend fun getMnemonicWordList(): Result<List<String>>
    suspend fun createWallet(): Result<String>
}
