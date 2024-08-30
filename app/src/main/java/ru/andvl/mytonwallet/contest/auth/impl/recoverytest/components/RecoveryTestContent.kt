package ru.andvl.mytonwallet.contest.auth.impl.recoverytest.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import ru.andvl.mytonwallet.contest.auth.impl.recoverytest.RecoveryTestAction
import ru.andvl.mytonwallet.contest.auth.impl.recoverytest.RecoveryTestState

@Composable
fun RecoveryTestContent(
    state: RecoveryTestState,
    onAction: (RecoveryTestAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        state.recoveryWords.fastForEach { word ->
            OutlinedTextField(
                value = word,
                onValueChange = { /*TODO*/ },
                label = { Text(text = word) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}
