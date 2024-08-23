package ru.andvl.mytonwallet.contest.ui.theme.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun ButtonPrimary(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (RowScope.() -> Unit)
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 13.dp
        ),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier,
        content = content
    )
}

@Composable
fun ButtonSecondary(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (RowScope.() -> Unit)
) {
    TextButton(
        onClick = onClick,
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 13.dp
        ),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        modifier = modifier,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonPreview() {
    MyTonWalletContestTheme {
        ButtonPrimary(
            onClick = {},
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Create New Wallet",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonSecondaryPreview() {
    MyTonWalletContestTheme {
        ButtonSecondary(
            onClick = {},
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Create New Wallet",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}