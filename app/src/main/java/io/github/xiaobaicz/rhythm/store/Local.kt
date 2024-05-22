package io.github.xiaobaicz.rhythm.store

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import io.github.xiaobaicz.rhythm.entity.Beat
import io.github.xiaobaicz.store.annotation.Serialize
import io.github.xiaobaicz.store.annotation.Store
import io.github.xiaobaicz.store.mmkv.MMKVStore
import io.github.xiaobaicz.store.serialize.gson.GsonSerialize
import io.github.xiaobaicz.store.store

@Store(MMKVStore::class)
@Serialize(GsonSerialize::class)
interface Local {
    var beat: Beat?
    var cycle: Int?
}

@Composable
fun rememberLocal(): Local {
    return remember { store() }
}