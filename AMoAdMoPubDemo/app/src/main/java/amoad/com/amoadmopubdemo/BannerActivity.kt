package amoad.com.amoadmopubdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubView
import kotlinx.android.synthetic.main.activity_banner.*

class BannerActivity : AppCompatActivity(), MoPubView.BannerAdListener {

    private var adUnitID = "eeb433b2c67848fb9148c6b091d8bb42"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)

        var moPubView = MoPubView(this)
        moPubView.adUnitId = this.adUnitID
        moPubView.bannerAdListener = this@BannerActivity
        moPubView.loadAd()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBannerLoaded(moPubView: MoPubView) {
        var content = banner_mopubview as ViewGroup
        content.addView(moPubView)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onBannerFailed(moPubView: MoPubView, moPubErrorCode: MoPubErrorCode) {
        Log.d("debug", "onBannerFailed")
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
}
