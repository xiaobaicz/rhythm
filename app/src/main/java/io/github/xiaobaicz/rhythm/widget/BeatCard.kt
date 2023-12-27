package io.github.xiaobaicz.rhythm.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.widget.doAfterTextChanged
import io.github.xiaobaicz.rhythm.databinding.WidgetBeatCardBinding
import io.github.xiaobaicz.rhythm.entity.Beat
import kotlin.math.max

class BeatCard : EditCard {
    private val bind = WidgetBeatCardBinding.inflate(LayoutInflater.from(context), this)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        bind.clearRelax.setOnClickListener { handleClear(bind.relax) }
        bind.clearHold.setOnClickListener { handleClear(bind.hold) }
        bind.relax.doAfterTextChanged { handleClearState(bind.clearRelax, it) }
        bind.hold.doAfterTextChanged { handleClearState(bind.clearHold, it) }
    }

    fun setBeat(beat: Beat?) {
        beat?.also {
            bind.relax.setText("${it.relax}")
            bind.hold.setText("${it.hold}")
        }
    }

    fun getBeat(): Beat = try {
        val relax = bind.relax.text?.toString()?.toInt() ?: 0
        val last = bind.hold.text?.toString()?.toInt() ?: 0
        Beat(max(relax, 1), max(last, 1))
    } catch (t: Throwable) {
        Beat(1, 1)
    }
}