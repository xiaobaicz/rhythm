package io.github.xiaobaicz.rhythm.design.runtime

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap

@Composable
fun <T> rememberMutableStateOf(factory: () -> T): MutableState<T> {
    return remember { mutableStateOf(factory()) }
}

@Composable
fun <T> rememberMutableStateListOf(): SnapshotStateList<T> {
    return remember { mutableStateListOf() }
}

@Composable
fun <K, V> rememberMutableStateMapOf(): SnapshotStateMap<K, V> {
    return remember { mutableStateMapOf() }
}

@Composable
fun <T> rememberMutableStateListOf(builder: () -> Array<T>): SnapshotStateList<T> {
    return remember { mutableStateListOf(*builder()) }
}

@Composable
fun <K, V> rememberMutableStateMapOf(builder: () -> Array<Pair<K, V>>): SnapshotStateMap<K, V> {
    return remember { mutableStateMapOf(*builder()) }
}