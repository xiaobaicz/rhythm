package io.github.xiaobaicz.rhythm.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.widget.doAfterTextChanged
import io.github.xiaobaicz.rhythm.databinding.WidgetLoopCardBinding

class LoopCard : EditCard {
    private val bind = WidgetLoopCardBinding.inflate(LayoutInflater.from(context), this)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        bind.clearLoop.setOnClickListener { handleClear(bind.loop) }
        bind.loop.doAfterTextChanged { handleClearState(bind.clearLoop, it) }
    }

    fun setLoop(loop: Int?) {
        loop?.also {
            bind.loop.setText("$it")
        }
    }

    fun getLoop(): Int = try {
        bind.loop.text?.toString()?.toInt() ?: 1
    } catch (t: Throwable) {
        1
    }
}