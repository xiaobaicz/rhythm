package io.github.xiaobaicz.rhythm.design.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import io.github.xiaobaicz.rhythm.design.Content
import io.github.xiaobaicz.rhythm.design.theme.LocalContentTextStyle
import io.github.xiaobaicz.rhythm.design.theme.localTextStyleScheme

@Composable
fun ContentTextStyleProvider(style: TextStyle = localTextStyleScheme.bodyMedium, content: Content) {
    CompositionLocalProvider(value = LocalContentTextStyle provides style) {
        content()
    }
}