package ru.andvl.mytonwallet.contest.auth.impl.biometriclock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.auth.impl.ui.WalletCreatedFlowTitleWithDescription
import ru.andvl.mytonwallet.contest.ui.components.ButtonStyle
import ru.andvl.mytonwallet.contest.ui.components.Loading
import ru.andvl.mytonwallet.contest.ui.components.LoadingStyle
import ru.andvl.mytonwallet.contest.ui.components.TonWalletButton
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun BiometricLockScreen(
    state: BiometricLockState,
    onAction: (BiometricLockAction) -> Unit,
    modifier: Modifier = Modifier
) {
    if (state is BiometricLockState.Loading) {
        Loading(
            modifier = modifier.fillMaxSize(),
            style = LoadingStyle.DIALOG
        )
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_fingerprint),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(124.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        WalletCreatedFlowTitleWithDescription(
            title = stringResource(R.string.auth_biometric_lock_title),
            description = stringResource(R.string.auth_biometric_lock_description),
            modifier = Modifier.padding(horizontal = 48.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        TonWalletButton(
            text = stringResource(R.string.auth_biometric_lock_enable),
            onClick = { onAction(BiometricLockAction.OnEnableClicked) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TonWalletButton(
            text = stringResource(R.string.auth_biometric_lock_skip),
            buttonStyle = ButtonStyle.SECONDARY,
            onClick = { onAction(BiometricLockAction.OnSkipClicked) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BiometricLockScreenPreview() {
    MyTonWalletContestTheme {
        BiometricLockScreen(
            state = BiometricLockState.Init,
            onAction = {},
        )
    }
}