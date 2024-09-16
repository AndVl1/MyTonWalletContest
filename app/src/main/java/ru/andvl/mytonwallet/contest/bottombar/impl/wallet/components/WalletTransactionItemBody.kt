package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.andvl.mytonwallet.contest.bottombar.impl.model.HistoryActivity
import ru.andvl.mytonwallet.contest.ui.theme.ProfitColor
import ru.andvl.mytonwallet.contest.utils.formatTokenAmountUsd
import ru.andvl.mytonwallet.contest.utils.formatTokenCurrency

@Composable
fun WalletTransactionItemBody(
    activity: HistoryActivity,
    modifier: Modifier = Modifier
) {
    when (activity) {
        is HistoryActivity.ReceivedTransaction -> {
            val token = activity.token
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "+${formatTokenCurrency(activity.amount, token.symbol ?: "")}",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.End,
                    color = ProfitColor
                )
                Text(
                    text = formatTokenAmountUsd(activity.amountUsd),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                    color = ProfitColor
                )
            }
        }

        is HistoryActivity.SentTransaction -> {
            val token = activity.token
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = formatTokenCurrency(activity.amount.abs(), token.symbol ?: ""),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = formatTokenAmountUsd(activity.amountUsd.abs()),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }

        is HistoryActivity.NftReceivedTransaction -> {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = activity.nft.name ?: activity.nft.address,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge,
                    )

                    activity.nft.collectionName?.let {
                        Text(
                            text = it,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                AsyncImage(
                    model = activity.nft.image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }

        is HistoryActivity.NftSentTransaction -> {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = activity.nft.name ?: activity.nft.address,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge,
                    )

                    activity.nft.collectionName?.let {
                        Text(
                            text = it,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                AsyncImage(
                    model = activity.nft.image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }

        is HistoryActivity.SwappedTransaction -> {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "+${
                        formatTokenCurrency(
                            activity.fromAmount,
                            activity.fromToken.symbol ?: ""
                        )
                    }",
                    style = MaterialTheme.typography.bodyLarge,
                    color = ProfitColor
                )
                Text(
                    text = "-${
                        formatTokenCurrency(
                            activity.toAmount,
                            activity.toToken.symbol ?: ""
                        )
                    }",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}