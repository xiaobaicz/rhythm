package io.github.xiaobaicz.rhythm.design.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.autoSaver
import androidx.compose.runtime.saveable.rememberSaveable
import java.io.Serializable
import kotlin.reflect.KProperty

class Save<V : Serializable>(
    private var value: V
) : Serializable {
    operator fun setValue(obj: V?, kProperty: KProperty<*>, value: V) {
        this.value = value
    }

    operator fun getValue(obj: V?, kProperty: KProperty<*>): V {
        return this.value
    }

    companion object {
        const val serialVersionUID: Long = 92357827539L
    }
}

@Composable
fun <V : Serializable> rememberSaveSerializable(
    vararg inputs: Any?,
    saver: Saver<V, out Any> = autoSaver(),
    key: String? = null,
    init: () -> V
): Save<V> {
    return rememberSaveable(*inputs, saver, key) {
        Save(init())
    }
}

