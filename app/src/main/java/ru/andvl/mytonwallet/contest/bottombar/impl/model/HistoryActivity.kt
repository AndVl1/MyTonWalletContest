package ru.andvl.mytonwallet.contest.bottombar.impl.model

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.LocalDateTime
import ru.andvl.mytonwallet.contest.R
import java.math.BigDecimal

sealed class HistoryActivity {
    abstract val nameRes: Int
    abstract val dateTime: LocalDateTime

    data class SentTransaction(
        override val nameRes: Int = R.string.main_wallet_transaction_sent,
        override val dateTime: LocalDateTime,
        val token: Token,
        val amount: BigDecimal,
        val amountUsd: BigDecimal,
        val message: String? = null,
        val to: String,
        val toName: String? = null,
        val toColor: Color,
        val fee: Float
    ) : HistoryActivity()

    data class ReceivedTransaction(
        override val nameRes: Int = R.string.main_wallet_transaction_received,
        override val dateTime: LocalDateTime,
        val token: Token,
        val amount: BigDecimal,
        val amountUsd: BigDecimal,
        val message: String? = null,
        val from: String,
        val fromName: String? = null,
        val fromColor: Color,
        val fee: Float
    ) : HistoryActivity()

    data class SwappedTransaction(
        override val nameRes: Int = R.string.main_wallet_transaction_received,
        override val dateTime: LocalDateTime,
        val fromToken: Token,
        val fromAmount: BigDecimal,
        val toToken: Token,
        val toAmount: BigDecimal,
        val fee: Float
    ) : HistoryActivity()

    data class NftReceivedTransaction(
        override val nameRes: Int = R.string.main_wallet_transaction_nft_received,
        override val dateTime: LocalDateTime,
        val from: String,
        val fromName: String? = null,
        val fromColor: Color,
        val fee: Float,
        val nft: Nft
    ) : HistoryActivity()

    data class NftSentTransaction(
        override val nameRes: Int = R.string.main_wallet_transaction_nft_sent,
        override val dateTime: LocalDateTime,
        val to: String,
        val toName: String? = null,
        val toColor: Color,
        val fee: Float,
        val nft: Nft
    ) : HistoryActivity()
}