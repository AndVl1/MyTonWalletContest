package ru.andvl.mytonwallet.contest.auth.impl.walletimport.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import kotlin.math.max

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletImportTopBar(
    scrollState: LazyListState,
    onBackClicked: () -> Unit,
    onTESTNavigate: () -> Unit, // TODO delete
    modifier: Modifier = Modifier
) {
    val topBarHeight = 64.dp
    val toolbarHeightPx = with(LocalDensity.current) { topBarHeight.toPx() }
    val titleOffset by remember {
        derivedStateOf {
            val offset = scrollState.firstVisibleItemScrollOffset.toFloat()
            val index = scrollState.firstVisibleItemIndex
            when {
                index > 0 -> 0f
                else -> max(toolbarHeightPx - offset, 0f)
            }
        }
    }

    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.auth_wallet_import_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .graphicsLayer { translationY = titleOffset }
                    .padding(start = 8.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                    tint = MaterialTheme.colorScheme.tertiary,
                    contentDescription = null
                )
            }
        },
        // TODO delete
        actions = {
            IconButton(onClick = onTESTNavigate) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            }
        },
        modifier = modifier
    )
}