package ru.andvl.mytonwallet.contest.auth.impl.setpasscode.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun NumberKeyboardButton(
    digit: String,
    letters: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(
            horizontal = 18.dp,
            vertical = 8.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        modifier = modifier.shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(10.dp),
            clip = false,
            ambientColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
            spotColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = digit,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.width(30.dp))

            Text(
                text = letters,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun NumberKeyboardButtonPreview() {
    MyTonWalletContestTheme {
        Surface(
            color = MaterialTheme.colorScheme.surfaceContainer
        ) {
            NumberKeyboardButton(
                digit = "2",
                letters = "ABC",
                onClick = {},
                modifier = Modifier
                    .padding(16.dp)
                    .width(126.dp)
            )
        }
    }
}