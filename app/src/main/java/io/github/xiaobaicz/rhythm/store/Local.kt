package io.github.xiaobaicz.rhythm.store

import io.github.xiaobaicz.rhythm.entity.Beat

interface Local {
    var beat: Beat?
    var cycle: Int?
}
