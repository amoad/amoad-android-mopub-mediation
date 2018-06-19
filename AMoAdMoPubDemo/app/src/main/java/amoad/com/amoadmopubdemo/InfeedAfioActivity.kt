package amoad.com.amoadmopubdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubView
import kotlinx.android.synthetic.main.activity_infeed_afio.*

class InfeedAfioActivity : AppCompatActivity(), MoPubView.BannerAdListener {

    private var adUnitID = "c3cba3cd32cd48d9a98d78c40aa8dd1d"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infeed_afio)

        updateBtn.setOnClickListener{ this.updateAfio() }
        this.loadAdView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    
    override fun onBannerLoaded(moPubView: MoPubView) {
        Log.d("debug", "onBannerLoaded")
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onBannerFailed(moPubView: MoPubView, moPubErrorCode: MoPubErrorCode) {
        Log.d("debug", "onBannerFailed : $moPubErrorCode")
    }

    override fun onBannerClicked(moPubView: MoPubView) {
        Log.d("debug", "onBannerClicked")
    }

    override fun onBannerExpanded(moPubView: MoPubView) {
        Log.d("debug", "onBannerExpanded")
    }

    override fun onBannerCollapsed(moPubView: MoPubView) {
        Log.d("debug", "onBannerCollapsed")
    }

    private fun loadAdView() {
        var adView = MoPubView(this)
        adView?.let {
            adView.adUnitId = this.adUnitID
            adView.bannerAdListener = this@InfeedAfioActivity
            mopubView.addView(adView)
            adView.loadAd()
        }
    }

    private fun updateAfio() {
        mopubView.removeAllViews()?.let {
            this.loadAdView()
        }
    }
}
