package ru.andvl.mytonwallet.contest.auth.impl.walletimport.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.auth.impl.ui.MnemonicTextField
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun ImportWordTextField(
    index: Int,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    suggestions: List<String> = emptyList(),
    isError: Boolean = false
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { expanded = true }
            )
    ) {
        MnemonicTextField(
            index = index,
            value = value,
            onValueChange = {
                onValueChange(it)
                expanded = true
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            isError = isError
        )

        if (suggestions.isNotEmpty()) {
            AnimatedVisibility(visible = expanded) {
                MnemonicSuggestionMenu(
                    words = suggestions,
                    onSuggestionClick = { expanded = false },
                    modifier = Modifier
                        .offset(y = (-56).dp)
                        .wrapContentWidth()
                        .widthIn(max = 244.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ImportWordTextFieldPreview() {
    MyTonWalletContestTheme {
        ImportWordTextField(
            index = 1,
            value = "w",
            onValueChange = {},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            suggestions = listOf("word", "world", "work", "weer"),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
        )
    }
}