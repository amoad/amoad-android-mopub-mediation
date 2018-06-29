package jp.co.cyberagent.amoad.amoadmopubdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubInterstitial
import kotlinx.android.synthetic.main.activity_interstitial_afio.*

class InterstitialAfioActivity : AppCompatActivity(), MoPubInterstitial.InterstitialAdListener {

    private var mMoPubInterstitial: MoPubInterstitial? = null
    private var adUnitID = "管理画面から取得したAd unit IDを指定してください"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interstitial_afio)
        updateBtn.setOnClickListener{ this.update() }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mMoPubInterstitial != null) {
            mMoPubInterstitial?.destroy()
            mMoPubInterstitial = null
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onInterstitialLoaded(interstitial: MoPubInterstitial) {
        interstitial.show()
    }

    override fun onInterstitialFailed(interstitial: MoPubInterstitial, errorCode: MoPubErrorCode) {}

    override fun onInterstitialShown(interstitial: MoPubInterstitial) {}

    override fun onInterstitialClicked(interstitial: MoPubInterstitial) {}

    override fun onInterstitialDismissed(interstitial: MoPubInterstitial) {}

    private fun update() {
        if (mMoPubInterstitial == null) {
            mMoPubInterstitial = MoPubInterstitial(this, adUnitID)
            mMoPubInterstitial?.interstitialAdListener = this@InterstitialAfioActivity
        }
        mMoPubInterstitial?.load()
    }
}
