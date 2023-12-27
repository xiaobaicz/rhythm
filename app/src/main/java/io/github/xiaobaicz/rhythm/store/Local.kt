package io.github.xiaobaicz.rhythm.store

import io.github.xiaobaicz.rhythm.entity.Beat
import vip.oicp.xiaobaicz.lib.store.mmkv.annotation.MMKVStore
import vip.oicp.xiaobaicz.lib.store.serialize.gson.annotation.GsonSerialize

@MMKVStore
@GsonSerialize
interface Local {
    var beat: Beat?
    var cycle: Int?
}