package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.bottombar.impl.model.HistoryActivity
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.ui.theme.ProfitColor
import ru.andvl.mytonwallet.contest.utils.formatTime

@Composable
fun WalletTransactionItem(
    activity: HistoryActivity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (activity !is HistoryActivity.SwappedTransaction) {
            Image( // TODO url
                painter = painterResource(R.drawable.toncoin),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
        } else {

        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            if (activity !is HistoryActivity.SwappedTransaction) {
                Text(
                    text = "UQBK...y8I1",
                    style = MaterialTheme.typography.titleMedium
                )
            } else {
                Row {
                    Text(
                        text = activity.from,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_double_arrow),
                        tint = MaterialTheme.colorScheme.tertiary,
                        contentDescription = null
                    )
                    Text(
                        text = activity.to,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            Text(
                text = stringResource(activity.nameRes) + " · " + formatTime(activity.dateTime),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "12 TON",
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = "\$96",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }

//    when (activity) {
//        is HistoryActivity.SentTransaction -> {
//            Text(text = "Sent")
//        }
//
//        is HistoryActivity.ReceivedTransaction -> {
//            Text(text = "Received")
//        }
//
//        is HistoryActivity.SwappedTransaction -> {
//            Text(text = "Swapped")
//        }
//
//        is HistoryActivity.NftReceivedTransaction -> {
//            Text(text = "NftReceived")
//        }
//
//        is HistoryActivity.NftSentTransaction -> {
//            Text(text = "NftSent")
//        }
//    }
}

@Composable
fun SentTransaction(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.toncoin),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "UQBK...y8I1",
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sent · 08:40",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "12 TON",
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = "\$96",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Composable
fun SwapTransaction(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.size(48.dp)) {
            Image(
                painter = painterResource(R.drawable.toncoin),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )
            Image(
                painter = painterResource(R.drawable.toncoin),
                contentDescription = null,
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
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row {
                Text(
                    text = "MY",
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_double_arrow),
                    tint = MaterialTheme.colorScheme.tertiary,
                    contentDescription = null
                )
                Text(
                    text = "TON",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Swapped · 07:36",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "+100 TON",
                style = MaterialTheme.typography.bodyLarge,
                color = ProfitColor
            )
            Text(
                text = "-1 000 MY",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Composable
fun SentNftTransaction(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.toncoin),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "alice.ton",
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sent NFT · 07:14",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "Cat #123",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = "Rich Cats",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(R.drawable.toncoin),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}

@Composable
fun ReceivedTransaction(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.toncoin),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "mario.t.me",
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Received · 08:40",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "10 TON",
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = "\$80",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Composable
fun ReceivedTransactionWithProfit(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.toncoin),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Crypto Bot",
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Received · 09:41",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "+100 TON",
                style = MaterialTheme.typography.bodyLarge,
                color = ProfitColor
            )
            Text(
                text = "\$800",
                style = MaterialTheme.typography.bodyMedium,
                color = ProfitColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SentTransactionPreview() {
    MyTonWalletContestTheme {
        SentTransaction(
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SwapTransactionPreview() {
    MyTonWalletContestTheme {
        SwapTransaction(
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SentNftTransactionPreview() {
    MyTonWalletContestTheme {
        SentNftTransaction(
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ReceivedTransactionPreview() {
    MyTonWalletContestTheme {
        ReceivedTransaction(
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ReceivedTransactionWithProfitPreview() {
    MyTonWalletContestTheme {
        ReceivedTransactionWithProfit(
            onClick = {},
        )
    }
}