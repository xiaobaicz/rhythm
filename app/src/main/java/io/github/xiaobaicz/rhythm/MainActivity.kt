package io.github.xiaobaicz.rhythm

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.composable
import io.github.xiaobaicz.rhythm.design.AppTheme
import io.github.xiaobaicz.rhythm.entity.Beat
import io.github.xiaobaicz.rhythm.navigation.NavHost
import io.github.xiaobaicz.rhythm.navigation.NavHostControllerProvider
import io.github.xiaobaicz.rhythm.navigation.Path
import io.github.xiaobaicz.rhythm.page.Action
import io.github.xiaobaicz.rhythm.page.Home
import io.github.xiaobaicz.rhythm.store.Local
import io.github.xiaobaicz.store.store

class MainActivity : AppCompatActivity() {

    private val local = store<Local>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                NavHostControllerProvider {
                    NavHost(startDestination = Path.home) {
                        composable(Path.home) { Home(local.beat ?: Beat(10, 10), local.cycle ?: 1) }
                        composable(Path.action) { Action(local.beat ?: Beat(10, 10), local.cycle ?: 1) }
                    }
                }
            }
        }
    }

}