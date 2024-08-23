package ru.andvl.mytonwallet.contest.ui.theme.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

enum class ButtonStyle {
    PRIMARY,
    SECONDARY
}

@Composable
fun TonWalletButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonStyle: ButtonStyle = ButtonStyle.PRIMARY
) {
    when (buttonStyle) {
        ButtonStyle.PRIMARY -> {
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
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        ButtonStyle.SECONDARY -> {
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
                modifier = modifier
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonPreview() {
    MyTonWalletContestTheme {
        TonWalletButton(
            text = stringResource(R.string.auth_import_existing_wallet),
            buttonStyle = ButtonStyle.PRIMARY,
            onClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonSecondaryPreview() {
    MyTonWalletContestTheme {
        TonWalletButton(
            text = stringResource(R.string.auth_import_existing_wallet),
            buttonStyle = ButtonStyle.SECONDARY,
            onClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}