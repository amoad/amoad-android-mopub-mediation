package jp.co.cyberagent.amoad.amoadmopubdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mopub.mobileads.AMoAdMoPubAdapterInterstitial
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubInterstitial
import kotlinx.android.synthetic.main.activity_interstitial.*

class InterstitialActivity : AppCompatActivity(), MoPubInterstitial.InterstitialAdListener {

    private var adUnitID = "管理画面から取得したAdUnitIDを指定してください"
    private var interstitial: MoPubInterstitial? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interstitial)
        showBtn.setOnClickListener{ show() }
    }

    override fun onDestroy() {
        interstitial?.destroy()
        AMoAdMoPubAdapterInterstitial.readSid()?.let {
            AMoAdMoPubAdapterInterstitial.closeInterstitial(it)
        }
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun show() { createAndLoadInterstitial() }

    internal fun createAndLoadInterstitial() {
        if(interstitial == null) {
            interstitial = MoPubInterstitial(this, adUnitID)
            interstitial?.interstitialAdListener = this@InterstitialActivity
        }
        interstitial?.load()
    }

    override fun onInterstitialLoaded(interstitial: MoPubInterstitial) { interstitial.show() }

    override fun onInterstitialFailed(interstitial: MoPubInterstitial, errorCode: MoPubErrorCode) {}

    override fun onInterstitialShown(interstitial: MoPubInterstitial) {}

    override fun onInterstitialClicked(interstitial: MoPubInterstitial) {}

    override fun onInterstitialDismissed(interstitial: MoPubInterstitial) {}
}
