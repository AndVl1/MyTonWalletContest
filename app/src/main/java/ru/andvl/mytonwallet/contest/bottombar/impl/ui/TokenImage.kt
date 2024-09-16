package ru.andvl.mytonwallet.contest.bottombar.impl.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import ru.andvl.mytonwallet.contest.bottombar.impl.model.TokenImage

@Composable
fun TokenImage(
    image: TokenImage,
    modifier: Modifier = Modifier
) {
    when (image) {
        is TokenImage.Resource -> {
            Image(
                painter = painterResource(image.resId),
                contentDescription = null,
                modifier = modifier
            )
        }

        is TokenImage.Url -> {
            AsyncImage(
                model = image.url,
                contentDescription = null,
                modifier = modifier
            )
        }
    }
}