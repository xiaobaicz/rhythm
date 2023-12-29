package io.github.xiaobaicz.rhythm

import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.tencent.mmkv.MMKV
import vip.oicp.xiaobaicz.lib.common.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initMMKV()
        initAdMob()
    }

    private fun initMMKV() {
        MMKV.initialize(this)
    }

    private fun initAdMob() {
        val configuration = RequestConfiguration.Builder()
            .setTagForChildDirectedTreatment(RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
            .build()
        MobileAds.setRequestConfiguration(configuration)
        MobileAds.initialize(this)
    }

}