package io.github.xiaobaicz.rhythm.design.foundation

import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import io.github.xiaobaicz.rhythm.design.Content
import io.github.xiaobaicz.rhythm.design.component.StateColor
import io.github.xiaobaicz.rhythm.design.component.localButtonColor

class ViuButtonIndication(
    private val enabled: Boolean,
    private val select: State<Boolean>? = null
) : Indication {

    class ViuIndicationInstance(
        private val enabled: Boolean,
        private val select: State<Boolean>?,
        private val buttonColor: StateColor,
        private val isPressedAsState: State<Boolean>,
        private val isFocusedAsState: State<Boolean>
    ) : IndicationInstance {
        override fun ContentDrawScope.drawIndication() {
            when {
                !enabled -> drawRect(color = buttonColor.disable, size = size)
                isFocusedAsState.value -> drawRect(color = buttonColor.focus, size = size)
                select?.value == true -> drawRect(color = buttonColor.select, size = size)
                else -> drawRect(color = buttonColor.default, size = size)
            }
            drawContent()
            if (isPressedAsState.value) {
                drawRect(color = Color.Black.copy(alpha = 0.1f), size = size)
            }
        }
    }

    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        val isPressedAsState = interactionSource.collectIsPressedAsState()
        val isFocusedAsState = interactionSource.collectIsFocusedAsState()
        val buttonColor = localButtonColor
        return remember(interactionSource, enabled, select, buttonColor) {
            ViuIndicationInstance(
                enabled,
                select,
                buttonColor,
                isPressedAsState,
                isFocusedAsState
            )
        }
    }

}

object NoIndication : Indication {

    object IndicationInstanceImpl : IndicationInstance {
        override fun ContentDrawScope.drawIndication() {
            drawContent()
        }
    }

    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        return IndicationInstanceImpl
    }

}

@Composable
fun IndicationProvider(indication: Indication = NoIndication, content: Content) {
    CompositionLocalProvider(value = LocalIndication provides indication) {
        content()
    }
}