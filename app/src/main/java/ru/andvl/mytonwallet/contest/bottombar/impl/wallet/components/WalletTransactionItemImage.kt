package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.bottombar.impl.model.HistoryActivity
import ru.andvl.mytonwallet.contest.bottombar.impl.ui.AccountImage
import ru.andvl.mytonwallet.contest.bottombar.impl.ui.TokenImage

@Composable
fun WalletTransactionItemImage(
    activity: HistoryActivity,
    modifier: Modifier = Modifier
) {
    when (activity) {
        is HistoryActivity.SwappedTransaction -> {
            Box(modifier.size(48.dp)) {
                if (activity.fromToken.image != null && activity.toToken.image != null) {
                    TokenImage(
                        image = activity.fromToken.image,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                    )

                    TokenImage(
                        image = activity.toToken.image,
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.background,
                                shape = CircleShape
                            )
                            .padding(2.dp)
                            .size(32.dp)
                            .clip(CircleShape)
                            .align(Alignment.BottomEnd)
                    )
                }
            }
        }

        is HistoryActivity.SentTransaction -> AccountImage(name = activity.toName)
        is HistoryActivity.ReceivedTransaction -> AccountImage(name = activity.fromName)
        is HistoryActivity.NftSentTransaction -> AccountImage(name = activity.toName)
        is HistoryActivity.NftReceivedTransaction -> AccountImage(name = activity.fromName)
    }
}