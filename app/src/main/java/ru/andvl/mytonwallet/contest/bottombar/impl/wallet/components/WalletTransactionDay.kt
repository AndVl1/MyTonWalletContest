package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.utils.formatHumanDay
import ru.andvl.mytonwallet.contest.utils.getCurrentDate

@Composable
fun WalletTransactionDay(
    day: LocalDate,
    modifier: Modifier = Modifier
) {
    Text(
        text = formatHumanDay(day, LocalContext.current),
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 16.dp,
                bottom = 8.dp
            )
    )
}

@Preview(showBackground = true)
@Composable
private fun WalletTransactionDayPreview() {
    MyTonWalletContestTheme {
        WalletTransactionDay(
            day = getCurrentDate().plus(DatePeriod(days = 2))
        )
    }
}