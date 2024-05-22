package io.github.xiaobaicz.rhythm.design.component

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import io.github.xiaobaicz.rhythm.design.theme.localContentColor
import io.github.xiaobaicz.rhythm.design.theme.localContentTextStyle

@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(),
) {
    val textStyle = localContentTextStyle + TextStyle(color = localContentColor) + style
    BasicText(text, style = textStyle, modifier = modifier)
}

@Composable
fun Text(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(),
) {
    val textStyle = localContentTextStyle + TextStyle(color = localContentColor) + style
    BasicText(text, style = textStyle, modifier = modifier)
}

@Preview
@Composable
fun PreviewText() {
    Text(
        text = "AAAA",
        style = TextStyle(
            fontSize = 20.sp,
            lineHeight = 30.sp,
            color = Color.Black
        ),
    )
}