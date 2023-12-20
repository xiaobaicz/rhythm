package io.github.xiaobaicz.rhythm.page

import android.os.Bundle
import io.github.xiaobaicz.rhythm.content.toPage
import vip.oicp.xiaobaicz.lib.common.app.AppCompatActivity

class Main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toPage<Config>()
        finish()
    }

}