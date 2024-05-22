package io.github.xiaobaicz.rhythm.design.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import io.github.xiaobaicz.rhythm.design.theme.localColorScheme

@Composable
fun Modifier.defaultFocus(
    focusRequester: FocusRequester = remember { FocusRequester() }
): Modifier {
    LaunchedEffect(key1 = focusRequester) {
        focusRequester.requestFocus()
    }
    return focusRequester(focusRequester)
}

fun Modifier.ifElse(
    cond: Boolean,
    yes: Modifier.() -> Modifier = { this },
    no: Modifier.() -> Modifier = { this },
): Modifier {
    return if (cond) {
        yes()
    } else {
        no()
    }
}

@Composable
fun Modifier.page(color: Color = localColorScheme.background): Modifier {
    return fillMaxSize().background(color)
}