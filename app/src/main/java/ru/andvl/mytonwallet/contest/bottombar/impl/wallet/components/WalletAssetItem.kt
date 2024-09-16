package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.bottombar.impl.model.AssetToken
import ru.andvl.mytonwallet.contest.bottombar.impl.model.AssetTokenType
import ru.andvl.mytonwallet.contest.bottombar.impl.model.TokenImage
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.ui.theme.ProfitColor
import ru.andvl.mytonwallet.contest.utils.formatPercent
import ru.andvl.mytonwallet.contest.utils.formatTokenAmountUsd
import ru.andvl.mytonwallet.contest.utils.formatTokenCurrency
import ru.andvl.mytonwallet.contest.utils.formatTokenPrice
import java.math.BigDecimal

@Composable
fun WalletAssetItem(
    assetToken: AssetToken,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (assetToken.image != null) {
            AssetTokenIcon(
                image = assetToken.image,
                isStacking = assetToken.type == AssetTokenType.STACKED && assetToken.apy != null
            )
        } else {
            Spacer(modifier = Modifier.size(48.dp))
        }

        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = assetToken.name,
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = formatTokenPrice(assetToken.price),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )

                if (assetToken.type == AssetTokenType.STACKED && assetToken.apy != null) {
                    Text(
                        text = stringResource(
                            R.string.main_wallet_asset_apy,
                            formatPercent(assetToken.apy)
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = ProfitColor
                    )
                }

                if (assetToken.type != AssetTokenType.STACKED && assetToken.change > 0) {
                    Text(
                        text = stringResource(
                            R.string.main_wallet_asset_change_up,
                            formatPercent(assetToken.change)
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = ProfitColor
                    )
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = assetToken.let { formatTokenCurrency(it.amount, it.symbol ?: "") },
                style = MaterialTheme.typography.bodyLarge,
                color = ProfitColor
            )
            Text(
                text = formatTokenAmountUsd(assetToken.amountUsd),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAssetItem() {
    MyTonWalletContestTheme {
        WalletAssetItem(
            assetToken = AssetToken(
                type = AssetTokenType.SIMPLE,
                slug = "",
                name = "Staked TON",
                image = TokenImage.Resource(R.drawable.toncoin),
                amount = BigDecimal(1000),
                amountUsd = BigDecimal(8000),
                price = 8f,
                symbol = "TON",
                change = 1.12f,
                apy = 4f
            ),
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}
