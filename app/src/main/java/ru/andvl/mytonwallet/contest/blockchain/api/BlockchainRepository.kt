package ru.andvl.mytonwallet.contest.blockchain.api

interface BlockchainRepository {
    suspend fun createWallet(): Result<String>
}
