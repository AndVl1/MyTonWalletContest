package ru.andvl.mytonwallet.contest.bottombar.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun AccountImage(
    modifier: Modifier = Modifier,
    name: String? = null,
) {
    val gradientColors = listOf(Color(0xFFFF885E), Color(0xFFFF516A))

    Box(
        modifier = modifier
            .size(48.dp)
            .background(
                brush = Brush.linearGradient(gradientColors),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        if (name != null) {
            Text(
                text = name.first().toString().uppercase(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.background
            )
        } else {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_anonymous),
                tint = MaterialTheme.colorScheme.background,
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountImageWithNamePreview() {
    MyTonWalletContestTheme {
        AccountImage(
            name = "alice.ton"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AccountImageAnonymousPreview() {
    MyTonWalletContestTheme {
        AccountImage()
    }
}