package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.ui.components.MyTonWalletBottomSheet
import ru.andvl.mytonwallet.contest.ui.theme.ListDividerColor
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun SentTransactionBottomSheetContent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 4.dp
                )
        ) {
            Text(
                text = "Sent",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    tint = MaterialTheme.colorScheme.tertiary,
                    contentDescription = null
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 24.dp
                )
        ) {
            Text(
                text = "-500 TON",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "\$1 234",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "\uD83E\uDD73 Happy Birthday! Thank you for being such an amazing friend. I cherish every moment we spend together \uD83E\uDDE1",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }
        HorizontalDivider(
            thickness = 0.5.dp,
            color = ListDividerColor
        )
        HorizontalDivider(
            thickness = 11.5.dp,
            color = MaterialTheme.colorScheme.tertiaryContainer,
        )
        Text(
            text = "Transaction Details",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(
                start = 20.dp,
                top = 16.dp,
                bottom = 8.dp
            )
        )

        // Transaction details
        Column {
            ListItemBoxWithDivider(
                hasDivider = true,
                dividerPaddingValues = PaddingValues(start = 20.dp)
            ) {
                TransactionDetailItemWithImage(
                    label = "To",
                    value = "alice.ton",
                    imageRes = R.drawable.toncoin
                )
            }
            ListItemBoxWithDivider(
                hasDivider = true,
                dividerPaddingValues = PaddingValues(start = 20.dp)
            ) {
                TransactionDetailItem(
                    label = "Amount",
                    value = "500 TON"
                )
            }
            TransactionDetailItem(
                label = "Fee",
                value = "0.25 TON"
            )
        }
        HorizontalDivider(
            thickness = 0.5.dp,
            color = ListDividerColor
        )
        HorizontalDivider(
            thickness = 11.5.dp,
            color = MaterialTheme.colorScheme.tertiaryContainer,
        )
        Text(
            text = "View in Explorer",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable { /*TODO*/ }
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                )
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SentTransactionBottomSheet(modifier: Modifier = Modifier) {
    MyTonWalletBottomSheet(onDismissRequest = { /*TODO*/ }) {
        SentTransactionBottomSheetContent()
    }
}

@Preview(showBackground = true)
@Composable
fun SentTransactionBottomSheetContentPreview(modifier: Modifier = Modifier) {
    MyTonWalletContestTheme {
        SentTransactionBottomSheetContent()
    }
}

@Preview
@Composable
fun SentTransactionBottomSheetPreview() {
    MyTonWalletContestTheme {
        SentTransactionBottomSheet()
    }
}