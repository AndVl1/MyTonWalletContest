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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.utils.generateVerticalGradient

@Composable
fun AccountImage(
    baseColor: Color,
    modifier: Modifier = Modifier,
    name: String? = null,
    size: Dp = 48.dp,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge,
) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                brush = generateVerticalGradient(baseColor),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        if (name != null) {
            Text(
                text = name.first().toString().uppercase(),
                style = textStyle,
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
            name = "alice.ton",
            baseColor = Color(0xFFFF516A)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AccountImageAnonymousPreview() {
    MyTonWalletContestTheme {
        AccountImage(
            baseColor = Color.Red,
            modifier = Modifier.size(48.dp)
        )
    }
}