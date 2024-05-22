package io.github.xiaobaicz.rhythm.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ColorScheme(
    val theme: Color,
    val button: Color,
    val error: Color,
    val grey333: Color,
    val grey33306: Color,
    val grey666: Color,
    val grey66608: Color,
    val grey999: Color,
    val greyPlaceholder: Color,
    val grey6F6F6F: Color,
    val white: Color,
    val whiteFFF08: Color,
    val whiteFFF04: Color,
    val whiteEEE: Color,
    val background: Color,
)

val colorScheme = ColorScheme(
    theme = Color(0xff99ccff),
    button = Color(0xff424242),
    error = Color(0xffdc453c),
    grey333 = Color(0xff333333),
    grey33306 = Color(0x99333333),
    grey666 = Color(0xff666666),
    grey66608 = Color(0xCC666666),
    grey999 = Color(0xff999999),
    greyPlaceholder = Color(0xff4f4f4f),
    grey6F6F6F = Color(0xff6f6f6f),
    white = Color(0xffffffff),
    whiteFFF08 = Color(0xCCffffff),
    whiteFFF04 = Color(0x66ffffff),
    whiteEEE = Color(0xffeeeeee),
    background = Color(0xffefefef),
)

val LocalColorScheme = staticCompositionLocalOf { colorScheme }

val LocalContentColor = staticCompositionLocalOf { colorScheme.grey333 }

inline val localColorScheme: ColorScheme @Composable get() = LocalColorScheme.current

inline val localContentColor: Color @Composable get() = LocalContentColor.current