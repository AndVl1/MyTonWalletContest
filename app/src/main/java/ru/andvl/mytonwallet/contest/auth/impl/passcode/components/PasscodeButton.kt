package ru.andvl.mytonwallet.contest.auth.impl.passcode.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun PasscodeButton(
    digit: String,
    letters: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        shape = CircleShape,
        onClick = onClick,
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.15f),
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = digit,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = letters,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.66f)
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun PasscodeButtonPreview() {
    MyTonWalletContestTheme {
        Surface(
            color = MaterialTheme.colorScheme.primary
        ) {
            PasscodeButton(
                digit = "2",
                letters = "WXYZ",
                onClick = {},
                modifier = Modifier
                    .padding(16.dp)
                    .size(80.dp)
            )
        }
    }
}