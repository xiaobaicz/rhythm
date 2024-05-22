package io.github.xiaobaicz.rhythm.design

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import io.github.xiaobaicz.rhythm.design.component.ContentColorProvider
import io.github.xiaobaicz.rhythm.design.component.ContentTextStyleProvider
import io.github.xiaobaicz.rhythm.design.foundation.IndicationProvider
import io.github.xiaobaicz.rhythm.design.theme.LocalColorScheme
import io.github.xiaobaicz.rhythm.design.theme.LocalTextStyleScheme
import io.github.xiaobaicz.rhythm.design.theme.colorScheme
import io.github.xiaobaicz.rhythm.design.theme.localColorScheme
import io.github.xiaobaicz.rhythm.design.theme.textStyleScheme

@Composable
fun AppTheme(content: Content) {
    CompositionLocalProvider(LocalColorScheme provides colorScheme) {
        CompositionLocalProvider(LocalTextStyleScheme provides textStyleScheme) {
            IndicationProvider {
                ContentColorProvider {
                    ContentTextStyleProvider {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(localColorScheme.background)
                        ) {
                            content()
                        }
                    }
                }
            }
        }
    }
}