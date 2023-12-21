package io.github.xiaobaicz.rhythm.widget

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import cc.xiaobaicz.widgets.text.AppCompatEdit

open class EditCard : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    protected fun handleClear(edit: AppCompatEdit) {
        edit.setText("")
        edit.requestFocus()
    }

    protected fun handleClearState(view: ImageView, it: Editable?) {
        val empty = TextUtils.isEmpty(it)
        val visible = view.isVisible
        when {
            empty && visible -> view.isGone = true
            !empty && !visible -> view.isVisible = true
        }
    }
}