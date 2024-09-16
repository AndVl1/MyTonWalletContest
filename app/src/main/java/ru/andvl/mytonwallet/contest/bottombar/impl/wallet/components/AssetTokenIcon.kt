package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.bottombar.impl.model.TokenImage
import ru.andvl.mytonwallet.contest.bottombar.impl.ui.TokenImage
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.ui.theme.OnProfitColor
import ru.andvl.mytonwallet.contest.ui.theme.ProfitColor

@Composable
fun AssetTokenIcon(
    image: TokenImage,
    modifier: Modifier = Modifier,
    isStacking: Boolean = false,
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = modifier
    ) {
        TokenImage(
            image = image,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        if (isStacking) {
            StackingTokenBadge(modifier = Modifier.offset(x = 2.dp, y = 2.dp))
        }
    }
}

@Composable
fun StackingTokenBadge(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                color = ProfitColor,
                shape = CircleShape
            )
            .border(
                width = 1.5.dp,
                color = OnProfitColor,
                shape = CircleShape
            )
            .size(20.dp)
    ) {
        Text(
            text = stringResource(R.string.percent),
            style = MaterialTheme.typography.labelLarge,
            color = OnProfitColor,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TokenIconPreview() {
    MyTonWalletContestTheme {
        AssetTokenIcon(
            image = TokenImage.Resource(R.drawable.toncoin),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TokenIconStackingPreview() {
    MyTonWalletContestTheme {
        AssetTokenIcon(
            image = TokenImage.Resource(R.drawable.toncoin),
            modifier = Modifier.padding(16.dp),
            isStacking = true
        )
    }
}

@Preview
@Composable
fun StackingTokenBadgePreview() {
    MyTonWalletContestTheme {
        StackingTokenBadge()
    }
}