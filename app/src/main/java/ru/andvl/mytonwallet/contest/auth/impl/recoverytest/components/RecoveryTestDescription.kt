package ru.andvl.mytonwallet.contest.auth.impl.recoverytest.components

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
fun RecoveryTestDescription(
    number1: Int,
    number2: Int,
    number3: Int
) {
    val text = stringResource(R.string.auth_recovery_test_description, number1, number2, number3)

    val annotatedString = buildAnnotatedString {
        val parts = text.split(number1.toString(), number2.toString(), number3.toString())

        append(parts[0])
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(number1.toString())
        }

        append(parts[1])
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(number2.toString())
        }

        append(parts[2])
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(number3.toString())
        }

        append(parts[3])
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
    RecoveryTestDescription(4, 12, 19)
}