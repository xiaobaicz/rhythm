package io.github.xiaobaicz.rhythm.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.xiaobaicz.log.log
import io.github.xiaobaicz.rhythm.R
import io.github.xiaobaicz.rhythm.component.AppBar
import io.github.xiaobaicz.rhythm.design.component.Button
import io.github.xiaobaicz.rhythm.design.component.Icon
import io.github.xiaobaicz.rhythm.design.component.StateColor
import io.github.xiaobaicz.rhythm.design.component.Text
import io.github.xiaobaicz.rhythm.design.component.TextField
import io.github.xiaobaicz.rhythm.design.component.ThemeButton
import io.github.xiaobaicz.rhythm.design.runtime.rememberMutableStateOf
import io.github.xiaobaicz.rhythm.design.theme.localColorScheme
import io.github.xiaobaicz.rhythm.design.theme.localTextStyleScheme
import io.github.xiaobaicz.rhythm.design.utils.page
import io.github.xiaobaicz.rhythm.entity.Beat
import io.github.xiaobaicz.rhythm.navigation.Path
import io.github.xiaobaicz.rhythm.navigation.localNavHostController
import io.github.xiaobaicz.rhythm.store.local

@Composable
fun Home(beat: Beat = Beat(), cycle: Int = 0) {
    Column(modifier = Modifier.page()) {
        var relax by rememberMutableStateOf { if (beat.relax == 0) "" else "${beat.relax}" }
        var hold by rememberMutableStateOf { if (beat.hold == 0) "" else "${beat.hold}" }
        var last by rememberMutableStateOf { if (cycle == 0) "" else "$cycle" }
        AppBar {
            Text(
                text = stringResource(id = R.string.config),
                style = localTextStyleScheme.titleMedium
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
                    .shadow(4.dp, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .background(localColorScheme.white)
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.relax),
                    style = localTextStyleScheme.bodyLarge
                )
                Box(modifier = Modifier) {
                    TextField(
                        value = relax,
                        onValueChange = { relax = it },
                        hint = stringResource(id = R.string.input_relaxation_time),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        onClick = { relax = "" },
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.clear),
                            modifier = Modifier.size(56.dp).padding(16.dp)
                        )
                    }
                }
                Text(
                    text = stringResource(id = R.string.hold),
                    style = localTextStyleScheme.bodyLarge
                )
                Box {
                    TextField(
                        value = hold,
                        onValueChange = { hold = it },
                        hint = stringResource(id = R.string.input_hold_time),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        onClick = { hold = "" },
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.clear),
                            modifier = Modifier.size(56.dp).padding(16.dp)
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
                    .shadow(4.dp, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .background(localColorScheme.white)
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.cycle),
                    style = localTextStyleScheme.bodyLarge
                )
                Box {
                    TextField(
                        value = last,
                        onValueChange = { last = it },
                        hint = stringResource(id = R.string.input_cycle_count),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        onClick = { last = "" },
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.clear),
                            modifier = Modifier.size(56.dp).padding(16.dp)
                        )
                    }
                }
            }

        }
        val navHostController = localNavHostController
        ThemeButton(
            onClick = {
                try {
                    local.beat = Beat(relax.safe2Int(), hold.safe2Int())
                    local.cycle = last.safe2Int()
                    navHostController.navigate(Path.action)
                } catch (t: Throwable) {
                    log(t)
                }
            },
            color = StateColor(localColorScheme.button),
            buttonColor = StateColor(localColorScheme.theme),
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth().height(56.dp),
        ) {
            Text(text = stringResource(id = R.string.start))
        }
    }
}

private fun String.safe2Int(): Int {
    return try {
        toInt()
    } catch (t: Throwable) {
        log(t)
        0
    }
}

@Preview
@Composable
private fun PrevHome() {
    Home()
}