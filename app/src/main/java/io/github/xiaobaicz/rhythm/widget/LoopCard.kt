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
        bind.clearCycle.setOnClickListener { handleClear(bind.cycle) }
        bind.cycle.doAfterTextChanged { handleClearState(bind.clearCycle, it) }
    }

    fun setCycle(cycle: Int?) {
        cycle?.also {
            bind.cycle.setText("$it")
        }
    }

    fun getCycle(): Int = try {
        bind.cycle.text?.toString()?.toInt() ?: 1
    } catch (t: Throwable) {
        1
    }
}