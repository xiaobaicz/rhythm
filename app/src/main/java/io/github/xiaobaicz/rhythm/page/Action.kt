package io.github.xiaobaicz.rhythm.page

import android.media.MediaPlayer
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import io.github.xiaobaicz.rhythm.R
import io.github.xiaobaicz.rhythm.databinding.PageActionBinding
import io.github.xiaobaicz.rhythm.entity.Beat
import io.github.xiaobaicz.rhythm.store.Local
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

        val cycle = local.cycle ?: 1
        val beat = local.beat ?: throw NullPointerException()

        player.setOnCompletionListener {
            if (isHold)
                player.start()
        }

        lifecycleScope.launch {
            timer(beat)
        }

        lifecycleScope.launch {
            start(beat, cycle)
        }
    }

    private var count: Int = 0

    private val s = 1L
    private val m = 60 * s
    private val h = 60 * m

    private suspend fun timer(beat: Beat) {
        bind.time.text = String.format("%02d:%02d:%02d", count / h, count % h / m, count % m)
        var time = count % (beat.relax + beat.hold)
        if (isHold) {
            time -= beat.relax
        }
        bind.statusTime.text = String.format("%02d:%02d:%02d", time / h, time % h / m, time % m)
        count++
        delay(1000L)
        timer(beat)
    }

    private suspend fun start(beat: Beat, cycle: Int) {
        repeat(cycle) {
            val count = it + 1
            bind.count.text = "$count"
            relax(beat.relax * 1000L)
            hold(beat.hold * 1000L)
        }
        finish()
    }

    private suspend fun relax(time: Long) {
        if (player.isPlaying)
            player.pause()
        isHold = false
        bind.status.text = getString(R.string.status_relax)
        delay(time)
    }

    private suspend fun hold(time: Long) {
        if (!player.isPlaying)
            player.start()
        isHold = true
        bind.status.text = getString(R.string.status_hold)
        delay(time)
    }

    override fun onDestroy() {
        player.release()
        super.onDestroy()
    }

}