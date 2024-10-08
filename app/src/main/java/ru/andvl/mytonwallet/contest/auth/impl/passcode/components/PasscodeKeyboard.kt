package ru.andvl.mytonwallet.contest.auth.impl.passcode.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import kotlinx.collections.immutable.ImmutableList
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeButtonItem
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeState
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun PasscodeKeyboard(
    buttons: ImmutableList<ImmutableList<PasscodeButtonItem>>,
    onClick: (PasscodeButtonItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        buttons.fastForEach { rowButtons ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                if (rowButtons.size < 3) {
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                    )
                }
                rowButtons.fastForEach { button ->
                    when (button) {
                        is PasscodeButtonItem.DigitButton -> {
                            PasscodeButton(
                                digit = button.digitWithDescription.digit.toString(),
                                letters = button.digitWithDescription.description,
                                onClick = { onClick(button) },
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                            )
                        }

                        is PasscodeButtonItem.ActionButton -> {
                            IconButton(
                                onClick = { onClick(button) },
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(button.icon),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PasscodeKeyboardPreview() {
    MyTonWalletContestTheme {
        PasscodeKeyboard(
            buttons = PasscodeState().keyboardButtons,
            onClick = {},
            modifier = Modifier.fillMaxWidth(0.9f)
        )
    }
}