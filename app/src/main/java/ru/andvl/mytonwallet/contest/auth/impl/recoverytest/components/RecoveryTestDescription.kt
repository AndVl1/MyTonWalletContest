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
import androidx.compose.ui.util.fastForEachIndexed
import ru.andvl.mytonwallet.contest.R

@Composable
fun RecoveryTestDescription(
    indexes: List<Int>
) {
    val startText = stringResource(R.string.auth_recovery_test_description_start)
    val andText = stringResource(R.string.and)

    val annotatedString = buildAnnotatedString {
        append(startText)
        append(" ")

        indexes.fastForEachIndexed { i, index ->
            if (i > 0 && i < indexes.size - 1) {
                append(", ")
            } else if (i == indexes.size - 1 && indexes.size > 1) {
                append(" $andText ")
            }

            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append((index + 1).toString())
            }
        }

        append(".")
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
    RecoveryTestDescription(listOf(4, 12, 19))
}