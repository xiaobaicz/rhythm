package io.github.xiaobaicz.rhythm.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.xiaobaicz.rhythm.design.Content
import io.github.xiaobaicz.rhythm.design.utils.nothing

val LocalNavHostController = compositionLocalOf<NavHostController> { nothing() }

inline val localNavHostController @Composable get() = LocalNavHostController.current

@Composable
fun NavHostControllerProvider(
    controller: NavHostController = rememberNavController(),
    content: Content,
) {
    CompositionLocalProvider(value = LocalNavHostController provides controller) {
        localNavHostController.setLifecycleOwner(LocalLifecycleOwner.current)
        content()
    }
}

@Composable
fun NavHost(
    controller: NavHostController = rememberNavController(),
    startDestination: String,
    builder: NavGraphBuilder.() -> Unit,
) {
    NavHostControllerProvider(controller) {
        NavHost(
            navController = localNavHostController,
            startDestination = startDestination,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            builder = builder
        )
    }
}