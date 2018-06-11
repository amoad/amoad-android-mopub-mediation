package amoad.com.amoadmopubdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubView
import kotlinx.android.synthetic.main.activity_banner.*

class BannerActivity : AppCompatActivity(), MoPubView.BannerAdListener {

    private var moPubView: MoPubView? = null
    var adUnitID: String? = "eeb433b2c67848fb9148c6b091d8bb42"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)

        if (moPubView == null) {
            moPubView = MoPubView(this)
            moPubView?.setAdUnitId(this.adUnitID)
            moPubView?.setBannerAdListener(this)
            moPubView?.setBannerAdListener(this@BannerActivity)
            moPubView?.setKeywords("")
            moPubView?.loadAd()
        }
    }

    override fun onBannerLoaded(moPubView: MoPubView) {
        this.moPubView = moPubView
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onDestroy() {
        moPubView!!.destroy()
        super.onDestroy()
    }

    override fun onBannerFailed(moPubView: MoPubView, moPubErrorCode: MoPubErrorCode) {

    }

    override fun onBannerClicked(moPubView: MoPubView) {

    }

    override fun onBannerExpanded(moPubView: MoPubView) {

    }

    override fun onBannerCollapsed(moPubView: MoPubView) {

    }
}
