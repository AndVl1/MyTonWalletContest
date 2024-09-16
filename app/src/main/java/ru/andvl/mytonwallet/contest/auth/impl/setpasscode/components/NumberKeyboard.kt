package ru.andvl.mytonwallet.contest.auth.impl.setpasscode.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import kotlinx.collections.immutable.ImmutableList
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.NumberKeyboardButtonItem
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.SetPasscodeState
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun NumberKeyboard(
    buttons: ImmutableList<ImmutableList<NumberKeyboardButtonItem>>,
    onClick: (NumberKeyboardButtonItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceContainer,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 12.dp,
                    bottom = 24.dp,
                    start = 12.dp,
                    end = 12.dp
                )
                .navigationBarsPadding()
        ) {
            buttons.fastForEach { rowButtons ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (rowButtons.size < 3) Spacer(modifier = Modifier.weight(1f))
                    rowButtons.fastForEach { button ->
                        when (button) {
                            is NumberKeyboardButtonItem.DigitButton -> {
                                NumberKeyboardButton(
                                    digit = button.digitWithDescription.digit.toString(),
                                    letters = button.digitWithDescription.description,
                                    onClick = { onClick(button) },
                                    modifier = Modifier.weight(1f)
                                )
                            }

                            is NumberKeyboardButtonItem.ActionButton -> {
                                NumberKeyboardIconButton(
                                    onClick = { onClick(button) },
                                    icon = ImageVector.vectorResource(button.icon),
                                    modifier = Modifier.weight(1f)
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
private fun PasscodeKeyboardPreview() {
    MyTonWalletContestTheme {
        NumberKeyboard(
            buttons = SetPasscodeState().keyboardButtons,
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}