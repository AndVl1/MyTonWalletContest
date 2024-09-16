package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.atTime
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.bottombar.impl.model.HistoryActivity
import ru.andvl.mytonwallet.contest.bottombar.impl.model.Nft
import ru.andvl.mytonwallet.contest.bottombar.impl.model.Token
import ru.andvl.mytonwallet.contest.bottombar.impl.model.TokenImage
import ru.andvl.mytonwallet.contest.ui.components.MyTonWalletBottomSheet
import ru.andvl.mytonwallet.contest.ui.theme.ListDividerColor
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.utils.getCurrentDate
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionBottomSheet(
    activity: HistoryActivity,
    onDismissRequest: () -> Unit,
    onViewInExplorerClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MyTonWalletBottomSheet(onDismissRequest = onDismissRequest) {
        TransactionBottomSheetContent(
            activity = activity,
            onDismissClicked = onDismissRequest,
            onViewInExplorerClicked = onViewInExplorerClicked,
            modifier = modifier
        )
    }
}

@Composable
fun TransactionBottomSheetContent(
    activity: HistoryActivity,
    onDismissClicked: () -> Unit,
    onViewInExplorerClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TransactionBottomSheetHeader(
            transactionName = stringResource(activity.nameRes),
            onDismissClicked = onDismissClicked
        )
        TransactionBottomSheetTitle(activity)
        HorizontalDivider(
            thickness = 0.5.dp,
            color = ListDividerColor
        )
        HorizontalDivider(
            thickness = 11.5.dp,
            color = MaterialTheme.colorScheme.tertiaryContainer,
        )
        TransactionBottomSheetBody(activity)
        HorizontalDivider(
            thickness = 0.5.dp,
            color = ListDividerColor
        )
        HorizontalDivider(
            thickness = 11.5.dp,
            color = MaterialTheme.colorScheme.tertiaryContainer,
        )
        Text(
            text = stringResource(R.string.main_wallet_transaction_view_in_explorer),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable(onClick = onViewInExplorerClicked)
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                )
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun ReceivedTransactionBottomSheetContentPreview(modifier: Modifier = Modifier) {
    MyTonWalletContestTheme {
        TransactionBottomSheetContent(
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
                fromColor = Color.Red,
                fee = 0.5f
            ),
            onDismissClicked = {},
            onViewInExplorerClicked = {}
        )
    }
}

@Preview
@Composable
private fun ReceivedTransactionBottomSheetPreview() {
    MyTonWalletContestTheme {
        TransactionBottomSheet(
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
                fromColor = Color.Red,
                fee = 0.5f
            ),
            onDismissRequest = {},
            onViewInExplorerClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SentTransactionBottomSheetContentPreview(modifier: Modifier = Modifier) {
    MyTonWalletContestTheme {
        TransactionBottomSheetContent(
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
                toColor = Color.Red,
                fee = 0.5f
            ),
            onDismissClicked = {},
            onViewInExplorerClicked = {})
    }
}

@Preview
@Composable
private fun SentTransactionBottomSheetPreview() {
    MyTonWalletContestTheme {
        TransactionBottomSheet(
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
                toColor = Color.Red,
                fee = 0.5f
            ),
            onDismissRequest = {},
            onViewInExplorerClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ReceivedNftTransactionBottomSheetContentPreview(modifier: Modifier = Modifier) {
    MyTonWalletContestTheme {
        TransactionBottomSheetContent(
            activity = HistoryActivity.NftReceivedTransaction(
                dateTime = getCurrentDate().atTime(hour = 12, minute = 0),
                from = "",
                fromName = "alice.ton",
                fromColor = Color.Red,
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
            onDismissClicked = {},
            onViewInExplorerClicked = {})
    }
}

@Preview
@Composable
private fun ReceivedNftTransactionBottomSheetPreview() {
    MyTonWalletContestTheme {
        TransactionBottomSheet(
            activity = HistoryActivity.NftReceivedTransaction(
                dateTime = getCurrentDate().atTime(hour = 12, minute = 0),
                from = "",
                fromName = "alice.ton",
                fromColor = Color.Red,
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
            onDismissRequest = {},
            onViewInExplorerClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SentNftTransactionBottomSheetContentPreview(modifier: Modifier = Modifier) {
    MyTonWalletContestTheme {
        TransactionBottomSheetContent(
            activity = HistoryActivity.NftSentTransaction(
                dateTime = getCurrentDate().atTime(hour = 12, minute = 0),
                to = "",
                toName = "alice.ton",
                toColor = Color.Red,
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
            onDismissClicked = {},
            onViewInExplorerClicked = {}
        )
    }
}

@Preview
@Composable
private fun SentNftTransactionBottomSheetPreview() {
    MyTonWalletContestTheme {
        TransactionBottomSheet(
            activity = HistoryActivity.NftSentTransaction(
                dateTime = getCurrentDate().atTime(hour = 12, minute = 0),
                to = "",
                toName = "alice.ton",
                toColor = Color.Red,
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
            onDismissRequest = {},
            onViewInExplorerClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SwappedTransactionBottomSheetContentPreview(modifier: Modifier = Modifier) {
    MyTonWalletContestTheme {
        TransactionBottomSheetContent(
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
            onDismissClicked = {},
            onViewInExplorerClicked = {}
        )
    }
}

@Preview
@Composable
private fun SwappedTransactionBottomSheetPreview() {
    MyTonWalletContestTheme {
        TransactionBottomSheet(
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
            onDismissRequest = {},
            onViewInExplorerClicked = {}
        )
    }
}