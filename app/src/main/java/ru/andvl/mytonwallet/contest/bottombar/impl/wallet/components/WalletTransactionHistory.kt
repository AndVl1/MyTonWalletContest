package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import ru.andvl.mytonwallet.contest.bottombar.impl.model.HistoryActivity

fun LazyListScope.walletTransactionHistory(
    history: List<HistoryActivity>,
    onHistoryItemClicked: (HistoryActivity) -> Unit,
) {
    itemsIndexed(history) { index, activity ->
        ListItemBoxWithDivider(
            hasDivider = index < history.size - 1
        ) {
            WalletTransactionItem(
                activity = activity,
                onClick = { onHistoryItemClicked(activity) }
            )
        }
    }
}