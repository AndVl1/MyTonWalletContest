package ru.andvl.mytonwallet.contest.mappers

import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.TokenDto
import ru.andvl.mytonwallet.contest.database.entities.TokenEntity

fun TokenDto.toEntity(): TokenEntity {
    return TokenEntity(
        slug = slug,
        name = name,
        symbol = symbol,
        decimals = decimals,
        minterAddress = minterAddress,
        image = image,
        isPopular = isPopular,
        cmcSlug = cmcSlug,
        color = color,
        price = quote.price,
        priceUsd = quote.priceUsd,
        percentChange24h = quote.percentChange24h
    )
}