package io.github.xiaobaicz.rhythm

import com.tencent.mmkv.MMKV
import vip.oicp.xiaobaicz.lib.common.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
    }

}