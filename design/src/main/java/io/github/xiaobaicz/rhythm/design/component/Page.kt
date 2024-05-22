package io.github.xiaobaicz.rhythm.design.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.xiaobaicz.rhythm.design.TypeContent

@Composable
fun BoxPage(modifier: Modifier = Modifier, content: TypeContent<BoxScope>) {
    Box(modifier = modifier.fillMaxSize()) {
        content()
    }
}

@Composable
fun RowPage(modifier: Modifier = Modifier, content: TypeContent<RowScope>) {
    Row(modifier = modifier.fillMaxSize()) {
        content()
    }
}

@Composable
fun ColumnPage(modifier: Modifier = Modifier, content: TypeContent<ColumnScope>) {
    Column(modifier = modifier.fillMaxSize()) {
        content()
    }
}