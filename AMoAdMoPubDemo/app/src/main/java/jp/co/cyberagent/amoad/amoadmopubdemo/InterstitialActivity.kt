package jp.co.cyberagent.amoad.amoadmopubdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubInterstitial
import kotlinx.android.synthetic.main.activity_interstitial.*

class InterstitialActivity : AppCompatActivity(), MoPubInterstitial.InterstitialAdListener {

    private var mMoPubInterstitial: MoPubInterstitial? = null
    private var adUnitID = "4981e692297148ffa953b5506892ebc5"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interstitial)

        loadBtn.setOnClickListener{ this.load() }
        showBtn.setOnClickListener{ this.show() }
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

    // InterstitialAdListener implementation
    override fun onInterstitialLoaded(interstitial: MoPubInterstitial) {}

    override fun onInterstitialFailed(interstitial: MoPubInterstitial, errorCode: MoPubErrorCode) {}

    override fun onInterstitialShown(interstitial: MoPubInterstitial) {}

    override fun onInterstitialClicked(interstitial: MoPubInterstitial) {}

    override fun onInterstitialDismissed(interstitial: MoPubInterstitial) {}

    private fun show() { mMoPubInterstitial?.show() }

    private fun load() {
        if(mMoPubInterstitial == null) {
            mMoPubInterstitial = MoPubInterstitial(this, adUnitID)
            mMoPubInterstitial?.setInterstitialAdListener(this@InterstitialActivity)
        }
        mMoPubInterstitial?.load()
    }
}
