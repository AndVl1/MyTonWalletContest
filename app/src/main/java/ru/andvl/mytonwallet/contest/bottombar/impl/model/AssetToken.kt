package ru.andvl.mytonwallet.contest.bottombar.impl.model

import androidx.compose.ui.graphics.painter.Painter
import java.math.BigDecimal

data class AssetToken(
    val type: AssetTokenType,
    val slug: String,
    val name: String,
    val image: Painter?,
    val amount: BigDecimal,
    val amountUsd: BigDecimal,
    val price: Float,
    val symbol: String? = null,
    val change: Float,
    val apy: Float? = null
)

/*
export type UserToken = {
  amount: bigint;
  name: string;
  symbol: string;
  image?: string;
  slug: string;
  price: number;
  priceUsd: number;
  decimals: number;
  change24h: number;
  isDisabled?: boolean;
  canSwap?: boolean;
  keywords?: string[];
  cmcSlug?: string;
  totalValue: string;
  color?: string;
};
*/
