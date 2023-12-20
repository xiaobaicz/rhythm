package io.github.xiaobaicz.rhythm.page

import android.os.Bundle
import io.github.xiaobaicz.rhythm.databinding.PageConfigBinding
import vip.oicp.xiaobaicz.lib.common.app.AppCompatActivity

class Config : AppCompatActivity() {

    private val bind by lazy { PageConfigBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
    }

}