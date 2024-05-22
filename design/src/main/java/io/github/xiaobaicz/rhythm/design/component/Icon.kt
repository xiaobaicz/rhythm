package io.github.xiaobaicz.rhythm.design.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toolingGraphicsLayer
import androidx.compose.ui.layout.ContentScale

@Composable
fun Icon(
    painter: Painter,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    val color = remember(tint) {
        if (tint == Color.Unspecified) {
            null
        } else {
            ColorFilter.tint(tint)
        }
    }
    Box(
        modifier = modifier
            .toolingGraphicsLayer()
            .paint(painter = painter, colorFilter = color, contentScale = ContentScale.Fit)
    ) {}
}
