package ru.andvl.mytonwallet.contest.auth.impl.walletimport.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import ru.andvl.mytonwallet.contest.R

@Composable
fun WalletImportDescription(wordsNumber: Int) {
    val startText = stringResource(R.string.auth_wallet_import_description_start)
    val secretWords = stringResource(
        R.string.auth_wallet_import_description_secret_words,
        wordsNumber
    )
    val endText = stringResource(R.string.auth_wallet_import_description_end)

    val annotatedString = buildAnnotatedString {
        append(startText)
        append(" ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(secretWords)
        }
        append(" ")
        append(endText)
    }

    Text(
        text = annotatedString,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
private fun RecoveryTestDescriptionPreview() {
    WalletImportDescription(24)
}