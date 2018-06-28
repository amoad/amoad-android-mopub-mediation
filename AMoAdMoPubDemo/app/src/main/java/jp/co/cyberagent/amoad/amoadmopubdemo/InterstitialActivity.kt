package jp.co.cyberagent.amoad.amoadmopubdemo

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.amoad.InterstitialAd
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubInterstitial
import kotlinx.android.synthetic.main.activity_interstitial.*

class InterstitialActivity : AppCompatActivity(), MoPubInterstitial.InterstitialAdListener {

    private var mMoPubInterstitial: MoPubInterstitial? = null
    private var adUnitID = "4981e692297148ffa953b5506892ebc5"

    companion object {
        var sid: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interstitial)

        showBtn.setOnClickListener{ this.show() }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        InterstitialAd.onConfigurationChanged(newConfig)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mMoPubInterstitial != null) {
            mMoPubInterstitial?.destroy()
            mMoPubInterstitial = null
            sid?.let {
                InterstitialAd.close(it)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    // InterstitialAdListener implementation
    override fun onInterstitialLoaded(interstitial: MoPubInterstitial) { interstitial.show() }

    override fun onInterstitialFailed(interstitial: MoPubInterstitial, errorCode: MoPubErrorCode) {}

    override fun onInterstitialShown(interstitial: MoPubInterstitial) {}

    override fun onInterstitialClicked(interstitial: MoPubInterstitial) {}

    override fun onInterstitialDismissed(interstitial: MoPubInterstitial) {}

    private fun show() {
        if(mMoPubInterstitial == null) {
            mMoPubInterstitial = MoPubInterstitial(this, adUnitID)
            mMoPubInterstitial?.interstitialAdListener = this@InterstitialActivity
        }
        mMoPubInterstitial?.load()
    }
}
