package io.github.xiaobaicz.rhythm.design

import androidx.compose.runtime.Composable

typealias Content = @Composable () -> Unit

typealias TypeContent<T> = @Composable T.() -> Unit
