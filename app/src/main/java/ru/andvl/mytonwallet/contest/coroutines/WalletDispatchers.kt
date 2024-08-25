package ru.andvl.mytonwallet.contest.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

object WalletDispatchers {
    val workStealingDispatcher: CoroutineDispatcher by lazy {
        Executors.newWorkStealingPool().asCoroutineDispatcher()
    }
}
