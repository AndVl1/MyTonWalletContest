package ru.andvl.mytonwallet.contest.auth.impl.passcode.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import ru.andvl.mytonwallet.contest.R

@Composable
fun PasscodeLockTitleWithDescription(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.auth_passcode_lock_screen_title),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(R.string.auth_passcode_lock_screen_description),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}