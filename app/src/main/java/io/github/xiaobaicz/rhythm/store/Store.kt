package io.github.xiaobaicz.rhythm.store

import io.github.xiaobaicz.store2.Store
import io.github.xiaobaicz.store2.saver.MMapSaver

private val factory by lazy { Store.Factory(MMapSaver) }

val localStore by lazy { factory.get<Local>() }

val local by localStore