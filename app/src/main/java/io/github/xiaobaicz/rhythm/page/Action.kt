package io.github.xiaobaicz.rhythm.page

import android.media.MediaPlayer
import android.os.Bundle
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

    private val player by lazy { MediaPlayer.create(this, R.raw.last) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        lifecycleScope.launch {
            val loop = local.loop ?: 1
            val beat = local.beat ?: throw NullPointerException()
            start(beat, loop)
        }

        lifecycleScope.launch {
            timer()
        }
    }

    private var count: Int = 0

    private suspend fun timer() {
        bind.time.text = "${count++}"
        delay(1000L)
        timer()
    }

    private suspend fun start(beat: Beat, loop: Int) {
        repeat(loop) {
            relax(beat.relax * 1000L)
            last(beat.last * 1000L)
        }
        finish()
    }

    private suspend fun relax(time: Long) {
        if (player.isPlaying)
            player.stop()
        delay(time)
    }

    private suspend fun last(time: Long) {
        if (!player.isPlaying)
            player.start()
        delay(time)
    }

    override fun onDestroy() {
        player.release()
        super.onDestroy()
    }

}