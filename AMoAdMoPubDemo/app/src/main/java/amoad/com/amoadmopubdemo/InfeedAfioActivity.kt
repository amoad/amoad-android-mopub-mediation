package amoad.com.amoadmopubdemo

import amoad.com.amoadmopubdemo.R.id.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubView
import kotlinx.android.synthetic.main.activity_infeed_afio.*

class InfeedAfioActivity : AppCompatActivity(), MoPubView.BannerAdListener {

    private var moPubView: MoPubView? = null
    private var adUnitID = "c3cba3cd32cd48d9a98d78c40aa8dd1d"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infeed_afio)

        if (moPubView == null) {
            moPubView = MoPubView(this)
            moPubView?.adUnitId = this.adUnitID
            moPubView?.bannerAdListener = this@InfeedAfioActivity
            moPubView?.loadAd()
        }

        updateBtn.setOnClickListener{ this.updateAfio() }
        clearBtn.setOnClickListener{ this.clearAfio() }
    }

    override fun onDestroy() {
        moPubView?.destroy()
        super.onDestroy()
    }
    
    override fun onBannerLoaded(moPubView: MoPubView) {
        this.moPubView = moPubView
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

    private fun updateAfio() {}

    private fun clearAfio() {}
}
