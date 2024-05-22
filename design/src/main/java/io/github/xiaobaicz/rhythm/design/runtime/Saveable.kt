package io.github.xiaobaicz.rhythm.design.runtime

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.staticCompositionLocalOf
import io.github.xiaobaicz.rhythm.design.Content
import io.github.xiaobaicz.rhythm.design.utils.nothing

@Composable
fun SaveableStateHolderProvider(
    holder: SaveableStateHolder = rememberSaveableStateHolder(),
    content: Content,
) {
    CompositionLocalProvider(value = LocalSaveableStateHolder provides holder) {
        content()
    }
}

val LocalSaveableStateHolder = staticCompositionLocalOf<SaveableStateHolder> { nothing() }

val localSaveableStateHolder @Composable get() = LocalSaveableStateHolder.current
