package ru.andvl.mytonwallet.contest.auth.impl.nowallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.ui.theme.components.ButtonStyle
import ru.andvl.mytonwallet.contest.ui.theme.components.TonWalletButton

@Composable
fun NoWalletScreen(
    onCreateClicked: () -> Unit,
    onImportClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.diamond),
            contentDescription = null,
            modifier = Modifier.size(124.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.auth_mytonwallet_title),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.auth_no_wallet_screen_description),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        TonWalletButton(
            text = stringResource(R.string.auth_create_new_wallet),
            onClick = onCreateClicked,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TonWalletButton(
            text = stringResource(R.string.auth_import_existing_wallet),
            buttonStyle = ButtonStyle.SECONDARY,
            onClick = onImportClicked,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoWalletScreenPreview() {
    MyTonWalletContestTheme {
        NoWalletScreen(
            onCreateClicked = {},
            onImportClicked = {}
        )
    }
}