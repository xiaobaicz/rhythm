package io.github.xiaobaicz.rhythm.page

import android.os.Bundle
import io.github.xiaobaicz.rhythm.content.toPage
import io.github.xiaobaicz.rhythm.databinding.PageConfigBinding
import io.github.xiaobaicz.rhythm.store.Local
import vip.oicp.xiaobaicz.lib.common.app.AppCompatActivity
import vip.oicp.xiaobaicz.lib.store.store
import kotlin.math.max

class Config : AppCompatActivity() {

    private val bind by lazy { PageConfigBinding.inflate(layoutInflater) }

    private val local = store<Local>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        bind.beat.setBeat(local.beat)
        bind.cycle.setCycle(local.loop)

        bind.start.setOnClickListener { start() }
    }

    private fun start() {
        local.beat = bind.beat.getBeat()
        local.loop = max(bind.cycle.getCycle(), 1)
        toPage<Action>()
    }

}