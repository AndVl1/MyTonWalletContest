package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.fastForEachIndexed
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.bottombar.impl.model.AssetToken
import ru.andvl.mytonwallet.contest.bottombar.impl.model.AssetTokenType
import ru.andvl.mytonwallet.contest.bottombar.impl.model.TokenImage
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import java.math.BigDecimal

@Composable
fun WalletAssets(
    assets: List<AssetToken>,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        assets.fastForEachIndexed { index, asset ->
            ListItemBoxWithDivider(
                hasDivider = index < assets.size - 1
            ) {
                WalletAssetItem(
                    assetToken = asset,
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
private fun WalletAssetsPreview() {
    MyTonWalletContestTheme {
        WalletAssets(
            assets = List(3) {
                AssetToken(
                    type = AssetTokenType.STACKED,
                    slug = "",
                    name = "Staked TON",
                    image = TokenImage.Resource(R.drawable.toncoin),
                    amount = BigDecimal(1000),
                    amountUsd = BigDecimal(8000),
                    price = 8f,
                    symbol = "TON",
                    change = 1.12f,
                    apy = 4f
                )
            },
            modifier = Modifier
        )
    }
}