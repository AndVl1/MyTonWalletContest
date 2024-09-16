package ru.andvl.mytonwallet.contest.mappers

import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.blockchain.util.TONCOIN_SLUG
import ru.andvl.mytonwallet.contest.bottombar.impl.model.Token
import ru.andvl.mytonwallet.contest.bottombar.impl.model.TokenImage
import ru.andvl.mytonwallet.contest.database.entities.TokenEntity

fun TokenEntity.toDomain(): Token {
    return Token(
        slug = slug,
        name = name,
        image = if (slug == TONCOIN_SLUG) {
            TokenImage.Resource(R.drawable.toncoin)
        } else {
            image?.let { TokenImage.Url(it) }
        },
        price = price.toFloat(),
        symbol = symbol
    )
}