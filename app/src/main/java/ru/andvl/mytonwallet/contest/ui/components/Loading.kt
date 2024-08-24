package ru.andvl.mytonwallet.contest.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.Dialog
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

enum class LoadingStyle {
    BOX,
    DIALOG
}

@Composable
fun Loading(
    modifier: Modifier = Modifier,
    indicatorSize: Dp? = null,
    style: LoadingStyle = LoadingStyle.BOX
) {
    val indicatorModifier = indicatorSize?.let { Modifier.size(it) } ?: Modifier

    when (style) {
        LoadingStyle.BOX -> {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = indicatorModifier)
            }
        }

        LoadingStyle.DIALOG -> {
            Dialog(onDismissRequest = { }) {
                Box(
                    modifier = modifier,
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(indicatorModifier)
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun LoadingBoxPreview() {
    MyTonWalletContestTheme {
        Loading(
            modifier = Modifier.fillMaxSize(),
            style = LoadingStyle.BOX
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun LoadingDialogPreview() {
    MyTonWalletContestTheme {
        Loading(
            modifier = Modifier.fillMaxSize(),
            style = LoadingStyle.DIALOG
        )
    }
}