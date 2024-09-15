package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEachIndexed
import ru.andvl.mytonwallet.contest.bottombar.impl.model.HistoryActivity

@Composable
fun WalletTransactionHistory(
    history: List<HistoryActivity>,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        history.fastForEachIndexed { index, activity ->
            ListItemBoxWithDivider(
                hasDivider = index < history.size - 1
            ) {
                WalletTransactionItem(activity, {})
            }
        }
    }
}