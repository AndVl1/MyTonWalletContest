package ru.andvl.mytonwallet.contest.auth.impl.setpasscode.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.SetPasscodeState
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun SetPasscodeTitleWithDescription(
    state: SetPasscodeState,
    modifier: Modifier = Modifier
) {
    AnimatedContent(
        targetState = state,
        transitionSpec = {
            fadeIn() togetherWith fadeOut() using SizeTransform(clip = false)
        },
        modifier = modifier,
        label = ""
    ) { targetState ->
        val titleRes = when (targetState) {
            is SetPasscodeState.SetUp -> R.string.auth_set_passcode_screen_title
            is SetPasscodeState.Confirm -> R.string.auth_set_passcode_screen_confirm_title
        }

        val descriptionRes = when (targetState) {
            is SetPasscodeState.SetUp -> R.string.auth_set_passcode_screen_description
            is SetPasscodeState.Confirm -> R.string.auth_set_passcode_screen_confirm_description
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(titleRes),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(
                    descriptionRes,
                    targetState.passcodeLength.value
                ),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun SetPasscodeTitleWithDescriptionPreview() {
    MyTonWalletContestTheme {
        SetPasscodeTitleWithDescription(state = SetPasscodeState.SetUp())
    }
}