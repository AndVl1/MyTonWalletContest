package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.lerp
import androidx.compose.ui.tooling.preview.Preview
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.utils.USD
import ru.andvl.mytonwallet.contest.utils.formatBalanceOrTransactionAmount

@Composable
fun WalletScreenTitleWithBalance(
    balance: Float,
    currencySymbol: String,
    scrollProgress: Float,
    modifier: Modifier = Modifier
) {
    val animatedTitleStyle = if (scrollProgress < 1f) {
        lerp(
            start = MaterialTheme.typography.titleLarge,
            stop = MaterialTheme.typography.titleSmall,
            fraction = scrollProgress
        )
    } else {
        MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.background)
    }

    val animatedPriceStyle = if (scrollProgress < 1f) {
        lerp(
            start = MaterialTheme.typography.displaySmall,
            stop = MaterialTheme.typography.titleMedium,
            fraction = scrollProgress
        )
    } else {
        MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.background)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.main_wallet_title),
            style = animatedTitleStyle,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.tertiary
        )
        Text(
            text = "${currencySymbol}${formatBalanceOrTransactionAmount(balance)}",
            style = animatedPriceStyle,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WalletScreenTitleWithBalancePreview() {
    MyTonWalletContestTheme {
        WalletScreenTitleWithBalance(
            balance = 12345.67f,
            currencySymbol = USD,
            scrollProgress = 0f
        )
    }
}