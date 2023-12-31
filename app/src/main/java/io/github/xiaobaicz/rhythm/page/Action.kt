package io.github.xiaobaicz.rhythm.page

import android.media.MediaPlayer
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import io.github.xiaobaicz.rhythm.R
import io.github.xiaobaicz.rhythm.databinding.PageActionBinding
import io.github.xiaobaicz.rhythm.entity.Beat
import io.github.xiaobaicz.rhythm.store.Local
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vip.oicp.xiaobaicz.lib.common.app.AppCompatActivity
import vip.oicp.xiaobaicz.lib.store.store

class Action : AppCompatActivity() {

    private val bind by lazy { PageActionBinding.inflate(layoutInflater) }

    private val local = store<Local>()

    private val player by lazy { MediaPlayer.create(this, R.raw.kn_part) }

    private var isHold = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        player.setOnCompletionListener {
            if (isHold)
                player.start()
        }

        lifecycleScope.launch {
            val cycle = local.cycle ?: 1
            val beat = local.beat ?: throw NullPointerException()
            start(beat, cycle)
        }

        lifecycleScope.launch {
            timer()
        }
    }

    private var count: Int = 0

    private val s = 1L
    private val m = 60 * s
    private val h = 60 * m

    private suspend fun timer() {
        bind.time.text = String.format("%02d:%02d:%02d", count / h, count % h / m, count % m)
        count++
        delay(1000L)
        timer()
    }

    private suspend fun start(beat: Beat, cycle: Int) {
        withContext(Dispatchers.IO) {
            repeat(cycle) {
                withContext(Dispatchers.Main) {
                    val count = it + 1
                    bind.count.text = "$count"
                }
                relax(beat.relax * 1000L)
                hold(beat.hold * 1000L)
            }
        }
        finish()
    }

    private suspend fun relax(time: Long) {
        if (player.isPlaying)
            player.pause()
        isHold = false
        delay(time)
    }

    private suspend fun hold(time: Long) {
        if (!player.isPlaying)
            player.start()
        isHold = true
        delay(time)
    }

    override fun onDestroy() {
        player.release()
        super.onDestroy()
    }

}