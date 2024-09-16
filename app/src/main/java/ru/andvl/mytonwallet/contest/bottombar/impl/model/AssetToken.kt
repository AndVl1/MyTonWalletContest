package ru.andvl.mytonwallet.contest.bottombar.impl.model

import java.math.BigDecimal

data class AssetToken(
    val type: AssetTokenType,
    val slug: String,
    val name: String,
    val image: TokenImage?,
    val amount: BigDecimal,
    val amountUsd: BigDecimal,
    val price: Float,
    val symbol: String? = null,
    val change: Float,
    val apy: Float? = null
)

sealed interface TokenImage {
    data class Resource(val resId: Int) : TokenImage
    data class Url(val url: String) : TokenImage
}

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
