package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.bottombar.impl.model.HistoryActivity
import ru.andvl.mytonwallet.contest.bottombar.impl.model.Nft
import ru.andvl.mytonwallet.contest.bottombar.impl.ui.TokenImage
import ru.andvl.mytonwallet.contest.ui.theme.ProfitVariantColor
import ru.andvl.mytonwallet.contest.utils.formatTokenAmountUsd
import ru.andvl.mytonwallet.contest.utils.formatTokenCurrency

@Composable
fun TransactionBottomSheetTitle(
    activity: HistoryActivity,
    modifier: Modifier = Modifier
) {
    when (activity) {
        is HistoryActivity.ReceivedTransaction -> ReceivedTransactionBottomSheetTitle(
            activity,
            modifier
        )

        is HistoryActivity.SentTransaction -> SentTransactionBottomSheetTitle(
            activity,
            modifier
        )

        is HistoryActivity.NftReceivedTransaction -> NftTransactionBottomSheetTitle(
            activity.nft,
            modifier
        )

        is HistoryActivity.NftSentTransaction -> NftTransactionBottomSheetTitle(
            activity.nft,
            modifier
        )

        is HistoryActivity.SwappedTransaction -> SwappedTransactionBottomSheetTitle(
            activity,
            modifier
        )
    }
}

@Composable
fun ReceivedTransactionBottomSheetTitle(
    activity: HistoryActivity.ReceivedTransaction,
    modifier: Modifier = Modifier
) {
    val token = activity.token
    var isDecrypted by rememberSaveable { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 24.dp
            )
    ) {
        Text(
            text = "+${formatTokenCurrency(activity.amount, token.symbol ?: "")}",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = ProfitVariantColor,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = formatTokenAmountUsd(activity.amountUsd),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.tertiary,
        )
        if (activity.encryptedComment != null && activity.comment != null) {
            Spacer(modifier = Modifier.height(16.dp))
            AnimatedContent(targetState = isDecrypted, label = "") { isDecryptedState ->
                if (isDecryptedState) {
                    Text(
                        text = activity.comment,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.main_wallet_encrypted_message),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.main_wallet_decrypt),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable { isDecrypted = true }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SentTransactionBottomSheetTitle(
    activity: HistoryActivity.SentTransaction,
    modifier: Modifier = Modifier
) {
    val token = activity.token
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 24.dp
            )
    ) {
        Text(
            text = "-${formatTokenCurrency(activity.amount.abs(), token.symbol ?: "")}",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = formatTokenAmountUsd(activity.amountUsd.abs()),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.tertiary,
        )
        activity.comment?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = it,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun NftTransactionBottomSheetTitle(
    nft: Nft,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 24.dp
            )
    ) {
        AsyncImage(
            model = nft.image,
            contentDescription = null,
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = nft.name ?: nft.address,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
            nft.collectionName?.let { collectionName ->
                Text(
                    text = collectionName,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }
        }
    }
}

@Composable
fun SwappedTransactionBottomSheetTitle(
    activity: HistoryActivity.SwappedTransaction,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 24.dp
            )
    ) {
        Box {
            TokenImage(
                image = activity.fromToken.image,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            TokenImage(
                image = activity.toToken.image,
                modifier = Modifier
                    .padding(
                        top = 24.dp,
                        start = 16.dp
                    )
                    .border(
                        width = 3.dp,
                        color = MaterialTheme.colorScheme.background,
                        shape = CircleShape
                    )
                    .padding(3.dp)
                    .size(48.dp)
                    .clip(CircleShape)
                    .align(Alignment.BottomEnd)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = "-${
                    formatTokenCurrency(
                        activity.fromAmount.abs(),
                        activity.fromToken.symbol ?: ""
                    )
                }",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "+${
                    formatTokenCurrency(
                        activity.toAmount.abs(),
                        activity.toToken.symbol ?: ""
                    )
                }",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = ProfitVariantColor,
            )
        }
    }
}