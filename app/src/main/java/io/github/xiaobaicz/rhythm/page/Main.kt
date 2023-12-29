package io.github.xiaobaicz.rhythm.page

import android.os.Bundle
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import io.github.xiaobaicz.rhythm.R
import io.github.xiaobaicz.rhythm.content.toPage
import vip.oicp.xiaobaicz.lib.common.app.AppCompatActivity
import vip.oicp.xiaobaicz.lib.common.log.println4List

class Main : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adRequest = AdRequest.Builder()
            .build()

        InterstitialAd.load(this, getString(R.string.interstitial_ad), adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
                toConfig()
            }
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
                interstitialAd.fullScreenContentCallback = fullScreenContentCallback
                interstitialAd.show(this@Main)
            }
        })
    }

    private fun toConfig() {
        toPage<Config>()
        finish()
    }

    private val fullScreenContentCallback = object : FullScreenContentCallback() {
        override fun onAdClicked() {
            toConfig()
        }

        override fun onAdDismissedFullScreenContent() {
            toConfig()
        }

        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
            toConfig()
        }
    }

}