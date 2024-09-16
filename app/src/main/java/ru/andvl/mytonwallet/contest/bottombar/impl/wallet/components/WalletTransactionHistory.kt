package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.util.fastForEach
import kotlinx.datetime.LocalDate
import ru.andvl.mytonwallet.contest.bottombar.impl.model.HistoryActivity

fun LazyListScope.walletTransactionHistory(
    history: Map<LocalDate, List<HistoryActivity>>,
    onHistoryItemClicked: (HistoryActivity) -> Unit,
) {
    items(
        items = history.entries.toList().sortedByDescending { it.key },
        key = { it.value }
    ) { entry ->
        WalletTransactionDay(day = entry.key)

        entry.value.fastForEach { activity ->
            ListItemBoxWithDivider(
                hasDivider = activity != entry.value.last()
            ) {
                WalletTransactionItem(
                    activity = activity,
                    onClick = { onHistoryItemClicked(activity) }
                )
            }
        }
    }
}