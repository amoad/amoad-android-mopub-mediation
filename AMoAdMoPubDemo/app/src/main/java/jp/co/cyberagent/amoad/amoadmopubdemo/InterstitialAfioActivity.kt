package jp.co.cyberagent.amoad.amoadmopubdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubInterstitial
import kotlinx.android.synthetic.main.activity_interstitial_afio.*

class InterstitialAfioActivity : AppCompatActivity(), MoPubInterstitial.InterstitialAdListener {

    private var adUnitID = "管理画面から取得したAd unit IDを指定してください"
    private var interstitialAfio: MoPubInterstitial? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interstitial_afio)
        showBtn.setOnClickListener{ show() }
    }

    override fun onDestroy() {
        interstitialAfio?.destroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun show() { createAndLoadInterstitialAfio() }

    internal fun createAndLoadInterstitialAfio() {
        if (interstitialAfio == null) {
            interstitialAfio = MoPubInterstitial(this, adUnitID)
            interstitialAfio?.interstitialAdListener = this@InterstitialAfioActivity
        }
        interstitialAfio?.load()
    }

    override fun onInterstitialLoaded(interstitial: MoPubInterstitial) { interstitial.show() }

    override fun onInterstitialFailed(interstitial: MoPubInterstitial, errorCode: MoPubErrorCode) {}

    override fun onInterstitialShown(interstitial: MoPubInterstitial) {}

    override fun onInterstitialClicked(interstitial: MoPubInterstitial) {}

    override fun onInterstitialDismissed(interstitial: MoPubInterstitial) {}
}
