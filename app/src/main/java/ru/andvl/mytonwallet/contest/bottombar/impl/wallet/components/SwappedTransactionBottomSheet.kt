package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.ui.components.MyTonWalletBottomSheet
import ru.andvl.mytonwallet.contest.ui.theme.ListDividerColor
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.ui.theme.ProfitVariantColor

@Composable
fun SwappedTransactionBottomSheetContent(modifier: Modifier = Modifier) {
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
                text = "Swapped",
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

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 24.dp
                )
        ) {
            Box {
                Image(
                    painter = painterResource(R.drawable.toncoin),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
                Image(
                    painter = painterResource(R.drawable.toncoin),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(
                            top = 24.dp,
                            start = 16.dp
                        )
                        .border(
                            width = 3.dp,
                            color = MaterialTheme.colorScheme.background,
                            shape = CircleShape
                        )
                        .padding(3.dp)
                        .size(48.dp)
                        .clip(CircleShape)
                        .align(Alignment.BottomEnd)

                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "-100 TON",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "+1 000 MY",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = ProfitVariantColor,
                )
            }
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
                TransactionDetailItem(
                    label = "Swapped at",
                    value = "31 Jan, 8:30"
                )
            }
            ListItemBoxWithDivider(
                hasDivider = true,
                dividerPaddingValues = PaddingValues(start = 20.dp)
            ) {
                TransactionDetailItem(
                    label = "Sent",
                    value = "100 TON"
                )
            }
            ListItemBoxWithDivider(
                hasDivider = true,
                dividerPaddingValues = PaddingValues(start = 20.dp)
            ) {
                TransactionDetailItem(
                    label = "Received",
                    value = "1000 MY"
                )
            }
            ListItemBoxWithDivider(
                hasDivider = true,
                dividerPaddingValues = PaddingValues(start = 20.dp)
            ) {
                TransactionDetailItem(
                    label = "Price per 1 MY",
                    value = "0.1 TON"
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
fun SwappedTransactionBottomSheet(modifier: Modifier = Modifier) {
    MyTonWalletBottomSheet(onDismissRequest = { /*TODO*/ }) {
        SwappedTransactionBottomSheetContent()
    }
}

@Preview(showBackground = true)
@Composable
fun SwappedTransactionBottomSheetContentPreview(modifier: Modifier = Modifier) {
    MyTonWalletContestTheme {
        SwappedTransactionBottomSheetContent()
    }
}

@Preview
@Composable
fun SwappedTransactionBottomSheetPreview() {
    MyTonWalletContestTheme {
        SwappedTransactionBottomSheet()
    }
}