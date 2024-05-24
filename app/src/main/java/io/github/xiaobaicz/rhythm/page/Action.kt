package io.github.xiaobaicz.rhythm.page

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import io.github.xiaobaicz.rhythm.R
import io.github.xiaobaicz.rhythm.component.AppBar
import io.github.xiaobaicz.rhythm.design.component.Text
import io.github.xiaobaicz.rhythm.design.runtime.rememberMutableStateOf
import io.github.xiaobaicz.rhythm.design.theme.localContentTextStyle
import io.github.xiaobaicz.rhythm.design.theme.localTextStyleScheme
import io.github.xiaobaicz.rhythm.design.utils.page
import io.github.xiaobaicz.rhythm.entity.Beat
import io.github.xiaobaicz.rhythm.navigation.localNavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.util.Locale

@Composable
fun Action(beat: Beat = Beat(0, 0), cycle: Int = 0) {
    Column(modifier = Modifier.page()) {
        AppBar {
            Text(
                text = stringResource(id = R.string.action),
                style = localTextStyleScheme.titleMedium
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val context = LocalContext.current
            val player = remember {
                MediaPlayer.create(context, R.raw.kn_part).apply {
                    setOnCompletionListener {
                        start()
                    }
                }
            }
            var time by rememberMutableStateOf { 0 }
            val timeFormat by remember { derivedStateOf { time.timeFormat() } }
            var count by rememberMutableStateOf { cycle }
            Text(text = "$count", style = localContentTextStyle.copy(fontSize = 48.sp))
            Text(text = timeFormat, style = localContentTextStyle.copy(fontSize = 48.sp))
            val navHostController = localNavHostController
            LaunchedEffect(key1 = Unit) {
                while (isActive) {
                    delay(1000)
                    if (count <= 0) break
                    time++
                    count = cycle - time / (beat.relax + beat.hold)
                    if (time % (beat.relax + beat.hold) < beat.relax) {
                        if (player.isPlaying) {
                            player.pause()
                        }
                    } else {
                        if (!player.isPlaying) {
                            player.start()
                        }
                    }
                }
                navHostController.popBackStack()
            }
            DisposableEffect(key1 = Unit) {
                onDispose { player.release() }
            }
        }
    }
}

private fun Int.timeFormat(): String {
    val h = this / 60 / 60
    val m = this / 60 % 60
    val s = this % 60
    return String.format(Locale.CHINA, "%02d:%02d:%02d", h, m, s)
}

@Preview
@Composable
private fun PrevAction() {
    Action()
}