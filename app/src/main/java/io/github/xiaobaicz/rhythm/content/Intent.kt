package io.github.xiaobaicz.rhythm.content

import android.app.Activity
import android.content.Intent

inline fun <reified T: Activity> Activity.toPage() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}