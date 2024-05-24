package io.github.xiaobaicz.rhythm.design.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import io.github.xiaobaicz.rhythm.design.theme.localContentColor
import io.github.xiaobaicz.rhythm.design.theme.localTextStyleScheme

@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    cursorBrush: Brush = SolidColor(Color.Black),
    paddingValues: PaddingValues = PaddingValues(vertical = 16.dp),
    decorationBox: @Composable (innerTextField: @Composable () -> Unit) -> Unit = { innerTextField ->
        Box(modifier = Modifier.padding(paddingValues), contentAlignment = Alignment.CenterStart) {
            innerTextField()
            if (value.isBlank()) {
                Text(
                    text = hint,
                    style = localTextStyleScheme.bodyMedium.copy(
                        color = localContentColor.copy(
                            alpha = 0.6f
                        )
                    ) + textStyle
                )
            }
        }
    }
) {
    BasicTextField(
        value,
        onValueChange,
        modifier,
        enabled,
        readOnly,
        localTextStyleScheme.bodyMedium.copy(color = localContentColor) + textStyle,
        keyboardOptions,
        keyboardActions,
        singleLine,
        maxLines,
        minLines,
        visualTransformation,
        onTextLayout,
        interactionSource,
        cursorBrush,
        decorationBox
    )
}

@Composable
fun TextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    cursorBrush: Brush = SolidColor(Color.Black),
    paddingValues: PaddingValues,
    decorationBox: @Composable (innerTextField: @Composable () -> Unit) -> Unit = { innerTextField ->
        Box(modifier = Modifier.padding(paddingValues)) {
            innerTextField()
            if (value.text.isBlank()) {
                Text(
                    text = hint,
                    style = localTextStyleScheme.bodyMedium.copy(
                        color = localContentColor.copy(
                            alpha = 0.6f
                        )
                    ) + textStyle
                )
            }
        }
    }
) {
    BasicTextField(
        value,
        onValueChange,
        modifier,
        enabled,
        readOnly,
        localTextStyleScheme.bodyMedium.copy(color = localContentColor) + textStyle,
        keyboardOptions,
        keyboardActions,
        singleLine,
        maxLines,
        minLines,
        visualTransformation,
        onTextLayout,
        interactionSource,
        cursorBrush,
        decorationBox
    )
}