package io.github.xiaobaicz.rhythm.design.painter

import android.graphics.drawable.AnimationDrawable
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap

private class AnimationDrawablePainter(
    private val drawable: AnimationDrawable,
    private val frameState: State<Int>,
) : Painter() {

    override val intrinsicSize: Size = Size(
        drawable.intrinsicWidth.toFloat(),
        drawable.intrinsicHeight.toFloat(),
    )

    override fun DrawScope.onDraw() {
        val frame = drawable.getFrame(frameState.value)
        drawImage(frame.toBitmap().asImageBitmap())
    }

}

private val AnimationDrawable.duration: Int
    get() {
        var time = 0
        repeat(numberOfFrames) {
            time += getDuration(it)
        }
        return time
    }

@Composable
private fun rememberAnimationDrawable(
    @DrawableRes resId: Int,
): AnimationDrawable {
    val context = LocalContext.current
    val theme = context.theme
    return remember(theme, resId) {
        ResourcesCompat.getDrawable(
            theme.resources,
            resId,
            theme,
        ) as AnimationDrawable
    }
}

@Composable
fun rememberAnimationDrawablePainter(
    @DrawableRes resId: Int,
    finish: () -> Unit,
): Painter {
    val drawable = rememberAnimationDrawable(resId)
    val frame = remember(resId) { mutableIntStateOf(0) }
    val frameState = animateIntAsState(
        targetValue = frame.intValue,
        label = "anim",
        animationSpec = TweenSpec(drawable.duration, 0, LinearEasing),
        finishedListener = { finish() }
    )
    val painter = remember(resId) {
        AnimationDrawablePainter(drawable, frameState)
    }
    LaunchedEffect(key1 = resId) {
        frame.intValue = drawable.numberOfFrames - 1
    }
    return painter
}
