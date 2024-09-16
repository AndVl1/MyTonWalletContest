package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.atTime
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.bottombar.impl.model.HistoryActivity
import ru.andvl.mytonwallet.contest.bottombar.impl.model.Nft
import ru.andvl.mytonwallet.contest.bottombar.impl.model.Token
import ru.andvl.mytonwallet.contest.bottombar.impl.model.TokenImage
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.utils.formatTime
import ru.andvl.mytonwallet.contest.utils.getCurrentDate
import java.math.BigDecimal

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
        WalletTransactionItemImage(activity = activity)
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            WalletTransactionItemTitle(activity)
            Text(
                text = stringResource(activity.nameRes) + " · " + formatTime(activity.dateTime),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
        }

        WalletTransactionItemBody(
            activity = activity,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SwapTransactionPreview() {
    MyTonWalletContestTheme {
        WalletTransactionItem(
            activity = HistoryActivity.SwappedTransaction(
                dateTime = getCurrentDate().atTime(hour = 12, minute = 0),
                fromToken = Token(
                    slug = "",
                    name = "Toncoin",
                    image = TokenImage.Resource(R.drawable.toncoin),
                    price = 0.5f,
                    symbol = "TON"
                ),
                fromAmount = BigDecimal(100),
                toToken = Token(
                    slug = "",
                    name = "Mycoin",
                    image = TokenImage.Resource(R.drawable.toncoin),
                    price = 0.5f,
                    symbol = "MY"
                ),
                toAmount = BigDecimal(1000),
                fee = 0.5f
            ),
            onClick = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun SentNftTransactionPreview() {
    MyTonWalletContestTheme {
        WalletTransactionItem(
            activity = HistoryActivity.NftSentTransaction(
                dateTime = getCurrentDate().atTime(hour = 12, minute = 0),
                to = "",
                toName = "alice.ton",
                fee = 0.5f,
                nft = Nft(
                    index = 1,
                    name = "TON Smart Challenge",
                    image = "https://cache.tonapi.io/imgproxy/d5OaB7xIgJW3-n5867cHUURCA8AcGdezG4AIlMsTR1I/rs:fill:1500:1500:1/g:no/aHR0cHM6Ly90b25lc2thbDI0MS5wYWdlcy5kZXYvSGFtczUxMzV0ZXIucG5n.webp",
                    collectionName = "TON Smart Challenge",
                    address = "",
                    thumbnail = ""
                )
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ReceivedTransactionPreview() {
    MyTonWalletContestTheme {
        WalletTransactionItem(
            activity = HistoryActivity.ReceivedTransaction(
                dateTime = getCurrentDate().atTime(hour = 12, minute = 0),
                token = Token(
                    slug = "",
                    name = "Toncoin",
                    image = TokenImage.Resource(R.drawable.toncoin),
                    symbol = "TON",
                    price = 0.5f
                ),
                amount = BigDecimal(0.000000001),
                amountUsd = BigDecimal(0.00000000055),
                from = "EQDAAp...Mlo1Oi",
                fromName = "alice.ton",
                fee = 0.5f
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SentTransactionPreview() {
    MyTonWalletContestTheme {
        WalletTransactionItem(
            activity = HistoryActivity.SentTransaction(
                dateTime = getCurrentDate().atTime(hour = 12, minute = 0),
                token = Token(
                    slug = "",
                    name = "Toncoin",
                    image = TokenImage.Resource(R.drawable.toncoin),
                    symbol = "TON",
                    price = 0.5f
                ),
                amount = BigDecimal(0.000000001),
                amountUsd = BigDecimal(0.00000000055),
                to = "",
                toName = "alice.ton",
                fee = 0.5f
            ),
            onClick = {}
        )
    }
}