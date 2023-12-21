package io.github.xiaobaicz.rhythm.widget

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import cc.xiaobaicz.widgets.text.AppCompatEdit
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
        bind.clearLast.setOnClickListener { handleClear(bind.last) }
        bind.relax.doAfterTextChanged { handleClearState(bind.clearRelax, it) }
        bind.last.doAfterTextChanged { handleClearState(bind.clearLast, it) }
    }

    fun setBeat(beat: Beat?) {
        beat?.also {
            bind.relax.setText("${it.relax}")
            bind.last.setText("${it.last}")
        }
    }

    fun getBeat(): Beat = try {
        val relax = bind.relax.text?.toString()?.toInt() ?: 0
        val last = bind.last.text?.toString()?.toInt() ?: 0
        Beat(max(relax, 1), max(last, 1))
    } catch (t: Throwable) {
        Beat(1, 1)
    }
}