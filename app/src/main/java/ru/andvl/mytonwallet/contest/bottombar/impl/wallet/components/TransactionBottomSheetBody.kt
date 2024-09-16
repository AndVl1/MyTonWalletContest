package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.blockchain.util.TONCOIN_SYMBOL
import ru.andvl.mytonwallet.contest.bottombar.impl.model.HistoryActivity
import ru.andvl.mytonwallet.contest.utils.formatLocalDateTime
import ru.andvl.mytonwallet.contest.utils.formatTokenCurrency
import ru.andvl.mytonwallet.contest.utils.getSwapRate

@Composable
fun TransactionBottomSheetBody(
    activity: HistoryActivity,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.main_wallet_transaction_details),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(
                start = 20.dp,
                top = 16.dp,
                bottom = 8.dp
            )
        )
        TransactionBottomSheetBodyContent(activity)
    }
}

@Composable
fun TransactionBottomSheetBodyContent(
    activity: HistoryActivity,
    modifier: Modifier = Modifier
) {
    when (activity) {
        is HistoryActivity.ReceivedTransaction -> ReceivedTransactionBottomSheetBodyContent(
            activity,
            modifier
        )

        is HistoryActivity.SentTransaction -> SentTransactionBottomSheetBodyContent(
            activity,
            modifier
        )

        is HistoryActivity.NftReceivedTransaction -> NftReceivedTransactionBottomSheetBodyContent(
            activity,
            modifier
        )

        is HistoryActivity.NftSentTransaction -> NftSentTransactionBottomSheetBodyContent(
            activity,
            modifier
        )

        is HistoryActivity.SwappedTransaction -> SwappedTransactionBottomSheetBodyContent(
            activity,
            modifier
        )
    }
}

@Composable
fun ReceivedTransactionBottomSheetBodyContent(
    activity: HistoryActivity.ReceivedTransaction,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        ListItemBoxWithDivider(
            hasDivider = true,
            dividerPaddingValues = PaddingValues(start = 20.dp)
        ) {
            TransactionDetailItem(
                label = stringResource(R.string.main_wallet_transaction_received_at),
                value = formatLocalDateTime(activity.dateTime)
            )
        }
        ListItemBoxWithDivider(
            hasDivider = true,
            dividerPaddingValues = PaddingValues(start = 20.dp)
        ) {
            if (activity.fromName != null) {
                TransactionDetailItemWithAccountImage(
                    label = stringResource(R.string.main_wallet_transaction_from),
                    value = activity.fromName,
                    name = activity.fromName,
                    baseColor = activity.fromColor
                )
            } else {
                TransactionDetailItem(
                    label = stringResource(R.string.main_wallet_transaction_from),
                    value = activity.from,
                    valueModifier = Modifier.padding(start = 24.dp)
                )
            }
        }
        ListItemBoxWithDivider(
            hasDivider = true,
            dividerPaddingValues = PaddingValues(start = 20.dp)
        ) {
            TransactionDetailItem(
                label = stringResource(R.string.main_wallet_transaction_amount),
                value = formatTokenCurrency(activity.amount.abs(), activity.token.symbol ?: "")
            )
        }
        TransactionDetailItem(
            label = stringResource(R.string.main_wallet_transaction_fee),
            value = formatTokenCurrency(activity.fee.toBigDecimal(), activity.token.symbol ?: "")
        )
    }
}

@Composable
fun SentTransactionBottomSheetBodyContent(
    activity: HistoryActivity.SentTransaction,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        ListItemBoxWithDivider(
            hasDivider = true,
            dividerPaddingValues = PaddingValues(start = 20.dp)
        ) {
            if (activity.toName != null) {
                TransactionDetailItemWithAccountImage(
                    label = stringResource(R.string.main_wallet_transaction_to),
                    value = activity.toName,
                    name = activity.toName,
                    baseColor = activity.toColor
                )
            } else {
                TransactionDetailItem(
                    label = stringResource(R.string.main_wallet_transaction_to),
                    value = activity.to,
                    valueModifier = Modifier.padding(start = 24.dp)
                )
            }
        }
        ListItemBoxWithDivider(
            hasDivider = true,
            dividerPaddingValues = PaddingValues(start = 20.dp)
        ) {
            TransactionDetailItem(
                label = stringResource(R.string.main_wallet_transaction_amount),
                value = formatTokenCurrency(activity.amount.abs(), activity.token.symbol ?: "")
            )
        }
        TransactionDetailItem(
            label = stringResource(R.string.main_wallet_transaction_fee),
            value = formatTokenCurrency(activity.fee.toBigDecimal(), activity.token.symbol ?: "")
        )
    }
}

@Composable
fun NftReceivedTransactionBottomSheetBodyContent(
    activity: HistoryActivity.NftReceivedTransaction,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        ListItemBoxWithDivider(
            hasDivider = true,
            dividerPaddingValues = PaddingValues(start = 20.dp)
        ) {
            TransactionDetailItem(
                label = stringResource(R.string.main_wallet_transaction_received_at),
                value = formatLocalDateTime(activity.dateTime)
            )
        }
        ListItemBoxWithDivider(
            hasDivider = true,
            dividerPaddingValues = PaddingValues(start = 20.dp)
        ) {
            TransactionDetailItem(
                label = stringResource(R.string.main_wallet_transaction_from),
                value = activity.fromName ?: activity.from,
                valueModifier = Modifier.padding(start = 24.dp)
            )
        }
        TransactionDetailItem(
            label = stringResource(R.string.main_wallet_transaction_fee),
            value = formatTokenCurrency(activity.fee.toBigDecimal(), TONCOIN_SYMBOL)
        )
    }
}

@Composable
fun NftSentTransactionBottomSheetBodyContent(
    activity: HistoryActivity.NftSentTransaction,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        ListItemBoxWithDivider(
            hasDivider = true,
            dividerPaddingValues = PaddingValues(start = 20.dp)
        ) {
            TransactionDetailItem(
                label = stringResource(R.string.main_wallet_transaction_sent_at),
                value = formatLocalDateTime(activity.dateTime)
            )
        }
        ListItemBoxWithDivider(
            hasDivider = true,
            dividerPaddingValues = PaddingValues(start = 20.dp)
        ) {
            TransactionDetailItem(
                label = stringResource(R.string.main_wallet_transaction_to),
                value = activity.toName ?: activity.to,
                valueModifier = Modifier.padding(start = 24.dp)
            )
        }
        TransactionDetailItem(
            label = stringResource(R.string.main_wallet_transaction_fee),
            value = formatTokenCurrency(activity.fee.toBigDecimal(), TONCOIN_SYMBOL)
        )
    }
}

@Composable
fun SwappedTransactionBottomSheetBodyContent(
    activity: HistoryActivity.SwappedTransaction,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        ListItemBoxWithDivider(
            hasDivider = true,
            dividerPaddingValues = PaddingValues(start = 20.dp)
        ) {
            TransactionDetailItem(
                label = stringResource(R.string.main_wallet_transaction_swapped_at),
                value = formatLocalDateTime(activity.dateTime)
            )
        }
        ListItemBoxWithDivider(
            hasDivider = true,
            dividerPaddingValues = PaddingValues(start = 20.dp)
        ) {
            TransactionDetailItem(
                label = stringResource(R.string.main_wallet_transaction_sent),
                value = formatTokenCurrency(activity.fromAmount, activity.fromToken.symbol ?: "")
            )
        }
        ListItemBoxWithDivider(
            hasDivider = true,
            dividerPaddingValues = PaddingValues(start = 20.dp)
        ) {
            TransactionDetailItem(
                label = stringResource(R.string.main_wallet_transaction_received),
                value = formatTokenCurrency(activity.toAmount, activity.toToken.symbol ?: "")
            )
        }
        ListItemBoxWithDivider(
            hasDivider = true,
            dividerPaddingValues = PaddingValues(start = 20.dp)
        ) {
            TransactionDetailItem(
                label = stringResource(
                    R.string.main_wallet_transaction_price_per_1,
                    activity.toToken.symbol ?: TONCOIN_SYMBOL
                ),
                value = getSwapRate(
                    activity.fromAmount,
                    activity.toAmount,
                    activity.fromToken,
                    activity.toToken
                )
            )
        }
        TransactionDetailItem(
            label = stringResource(R.string.main_wallet_transaction_fee),
            value = formatTokenCurrency(activity.fee.toBigDecimal(), TONCOIN_SYMBOL)
        )
    }
}