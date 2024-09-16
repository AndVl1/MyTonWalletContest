package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.bottombar.impl.model.HistoryActivity
import ru.andvl.mytonwallet.contest.bottombar.impl.utils.getShortenAddress

@Composable
fun WalletTransactionItemTitle(activity: HistoryActivity) {
    when (activity) {
        is HistoryActivity.SwappedTransaction -> {
            Row {
                Text(
                    text = activity.fromToken.symbol ?: activity.fromToken.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_double_arrow),
                    tint = MaterialTheme.colorScheme.tertiary,
                    contentDescription = null
                )
                Text(
                    text = activity.toToken.symbol ?: activity.toToken.name,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        is HistoryActivity.SentTransaction -> {
            Text(
                text = activity.toName ?: getShortenAddress(activity.to),
                style = MaterialTheme.typography.titleMedium
            )
        }

        is HistoryActivity.ReceivedTransaction -> {
            Text(
                text = activity.fromName ?: getShortenAddress(activity.from),
                style = MaterialTheme.typography.titleMedium
            )
        }

        is HistoryActivity.NftSentTransaction -> {
            Text(
                text = activity.toName ?: getShortenAddress(activity.to),
                style = MaterialTheme.typography.titleMedium
            )
        }

        is HistoryActivity.NftReceivedTransaction -> {
            Text(
                text = activity.fromName ?: getShortenAddress(activity.from),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
