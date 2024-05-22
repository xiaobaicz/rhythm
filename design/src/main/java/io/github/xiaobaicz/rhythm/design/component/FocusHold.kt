package io.github.xiaobaicz.rhythm.design.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import io.github.xiaobaicz.rhythm.design.Content
import io.github.xiaobaicz.rhythm.design.runtime.SaveableStateHolderProvider
import io.github.xiaobaicz.rhythm.design.runtime.localSaveableStateHolder

@Composable
fun FocusHold(
    modifier: Modifier = Modifier,
    parent: FocusRequester = remember { FocusRequester() },
    default: FocusRequester = FocusRequester.Default,
    holder: SaveableStateHolder = rememberSaveableStateHolder(),
    content: Content,
) {
    SaveableStateHolderProvider(holder = holder) {
        Box(modifier = modifier.focusGroup(parent, default).focusTarget()) {
            content()
        }
    }
}

@Composable
fun FocusItem(
    key: Any,
    modifier: Modifier = Modifier,
    holder: SaveableStateHolder = localSaveableStateHolder,
    content: Content
) {
    holder.SaveableStateProvider(key = key) {
        content()
    }
}

@Composable
fun Modifier.focusGroup(
    default: FocusRequester = FocusRequester.Default
): Modifier = focusGroup(
    parent = remember { FocusRequester() },
    default = default
)

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.focusGroup(
    parent: FocusRequester,
    default: FocusRequester = FocusRequester.Default,
): Modifier = focusRequester(parent).focusProperties {
    canFocus = false
    enter = { if (parent.restoreFocusedChild()) FocusRequester.Cancel else default }
    exit = { parent.saveFocusedChild().let { FocusRequester.Default } }
}
