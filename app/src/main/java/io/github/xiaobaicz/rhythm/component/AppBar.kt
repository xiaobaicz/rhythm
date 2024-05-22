package io.github.xiaobaicz.rhythm.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.xiaobaicz.rhythm.design.TypeContent
import io.github.xiaobaicz.rhythm.design.theme.localColorScheme

@Composable
fun AppBar(modifier: Modifier = Modifier, content: TypeContent<BoxScope>) {
    Box(
        modifier = modifier.fillMaxWidth()
            .height(56.dp)
            .background(localColorScheme.theme)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}