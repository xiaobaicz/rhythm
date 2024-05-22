package io.github.xiaobaicz.rhythm.design.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import io.github.xiaobaicz.rhythm.design.Content
import io.github.xiaobaicz.rhythm.design.theme.LocalContentColor
import io.github.xiaobaicz.rhythm.design.theme.localContentColor

@Composable
fun ContentColorProvider(color: Color = localContentColor, content: Content) {
    CompositionLocalProvider(value = LocalContentColor provides color) {
        content()
    }
}