package io.github.xiaobaicz.rhythm.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class TextStyleScheme(
    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headlineSmall: TextStyle,

    val subtitle: TextStyle,

    val titleLarge: TextStyle,
    val titleLargeSemiBold: TextStyle,
    val titleMedium: TextStyle,
    val titleMediumSemiBold: TextStyle,

    val bodyXLarge: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val bodyXSmall: TextStyle,

    val buttonLarge: TextStyle,
    val buttonMedium: TextStyle,
    val buttonSmall: TextStyle,
    val buttonXSmall: TextStyle,
    val buttonXXSmall: TextStyle,
)

val textStyleScheme = TextStyleScheme(
    headlineLarge = newTextStyle(fontSize = 34.sp, lineHeight = 41.sp, fontWeight = FontWeight.W700),
    headlineMedium = newTextStyle(fontSize = 30.sp, lineHeight = 39.sp, fontWeight = FontWeight.W700),
    headlineSmall = newTextStyle(fontSize = 27.5.sp, lineHeight = 36.sp, fontWeight = FontWeight.W700),

    subtitle = newTextStyle(fontSize = 30.sp, lineHeight = 39.sp, fontWeight = FontWeight.W500),

    titleLarge = newTextStyle(fontSize = 25.sp, lineHeight = 32.sp, fontWeight = FontWeight.W400),
    titleLargeSemiBold = newTextStyle(fontSize = 25.sp, lineHeight = 32.sp, fontWeight = FontWeight.W600),
    titleMedium = newTextStyle(fontSize = 22.5.sp, lineHeight = 29.sp, fontWeight = FontWeight.W400),
    titleMediumSemiBold = newTextStyle(fontSize = 22.5.sp, lineHeight = 29.sp, fontWeight = FontWeight.W600),

    bodyXLarge = newTextStyle(fontSize = 20.sp, lineHeight = 30.sp, fontWeight = FontWeight.W600),
    bodyLarge = newTextStyle(fontSize = 18.sp, lineHeight = 27.sp, fontWeight = FontWeight.W400),
    bodyMedium = newTextStyle(fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.W400),
    bodySmall = newTextStyle(fontSize = 15.sp, lineHeight = 22.5.sp, fontWeight = FontWeight.W400),
    bodyXSmall = newTextStyle(fontSize = 13.sp, lineHeight = 16.sp, fontWeight = FontWeight.W400),

    buttonLarge = newTextStyle(fontSize = 20.sp, lineHeight = 30.sp, fontWeight = FontWeight.W400),
    buttonMedium = newTextStyle(fontSize = 18.sp, lineHeight = 27.sp, fontWeight = FontWeight.W400),
    buttonSmall = newTextStyle(fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.W400),
    buttonXSmall = newTextStyle(fontSize = 15.sp, lineHeight = 22.5.sp, fontWeight = FontWeight.W400),
    buttonXXSmall = newTextStyle(fontSize = 13.sp, lineHeight = 16.sp, fontWeight = FontWeight.W400),
)

val LocalTextStyleScheme = staticCompositionLocalOf { textStyleScheme }

inline val localTextStyleScheme: TextStyleScheme @Composable get() = LocalTextStyleScheme.current

val LocalContentTextStyle = staticCompositionLocalOf { textStyleScheme.bodyMedium }

inline val localContentTextStyle @Composable get() = LocalContentTextStyle.current

fun newTextStyle(
    fontSize: TextUnit,
    lineHeight: TextUnit,
    color: Color = Color.Unspecified,
    fontWeight: FontWeight? = null,
    fontStyle: FontStyle? = null,
    textAlign: TextAlign = TextAlign.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    shadow: Shadow? = null,
    textDecoration: TextDecoration? = null,
): TextStyle {
    return TextStyle(
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        fontStyle = fontStyle,
        lineHeight = lineHeight,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        shadow = shadow,
        textDecoration = textDecoration,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        lineHeightStyle = LineHeightStyle(
            LineHeightStyle.Alignment.Center,
            LineHeightStyle.Trim.None
        )
    )
}