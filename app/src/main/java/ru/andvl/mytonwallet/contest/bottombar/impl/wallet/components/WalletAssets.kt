package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.bottombar.impl.model.AssetToken
import ru.andvl.mytonwallet.contest.bottombar.impl.model.AssetTokenType
import ru.andvl.mytonwallet.contest.bottombar.impl.model.TokenImage
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import java.math.BigDecimal

fun LazyListScope.walletAssets(assets: List<AssetToken>) {
    itemsIndexed(assets) { index, asset ->
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

@Preview
@Composable
private fun WalletAssetsPreview() {
    MyTonWalletContestTheme {
        LazyColumn {
            walletAssets(
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
                }
            )
        }
    }
}