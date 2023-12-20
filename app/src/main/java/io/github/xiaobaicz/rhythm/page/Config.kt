package io.github.xiaobaicz.rhythm.page

import android.os.Bundle
import io.github.xiaobaicz.rhythm.content.toPage
import io.github.xiaobaicz.rhythm.databinding.PageConfigBinding
import io.github.xiaobaicz.rhythm.entity.Beat
import io.github.xiaobaicz.rhythm.store.Local
import vip.oicp.xiaobaicz.lib.common.app.AppCompatActivity
import vip.oicp.xiaobaicz.lib.store.store

class Config : AppCompatActivity() {

    private val bind by lazy { PageConfigBinding.inflate(layoutInflater) }

    private val local = store<Local>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        bind.start.setOnClickListener {
            try {
                val relax = bind.relax.text?.toString()?.toInt() ?: 0
                val last = bind.last.text?.toString()?.toInt() ?: 0
                val loop = bind.loop.text?.toString()?.toInt() ?: 1
                if (last == 0) return@setOnClickListener
                local.beat = Beat(relax, last)
                local.loop = if (loop == 0) 1 else loop
                toPage<Action>()
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }

        local.beat?.also {
            bind.relax.setText("${it.relax}")
            bind.last.setText("${it.last}")
        }

        local.loop?.also {
            bind.loop.setText("$it")
        }
    }

}