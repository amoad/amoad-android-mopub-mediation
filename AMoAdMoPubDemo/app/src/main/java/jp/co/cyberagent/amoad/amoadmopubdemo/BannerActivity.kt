package jp.co.cyberagent.amoad.amoadmopubdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubView
import kotlinx.android.synthetic.main.activity_banner.*

class BannerActivity : AppCompatActivity(), MoPubView.BannerAdListener {

    private var moPubView: MoPubView? = null
    private var adUnitID = "管理画面から取得したAd unit IDを指定してください"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)

        if (moPubView == null) {
            moPubView = adView
            moPubView?.adUnitId = this.adUnitID
            moPubView?.bannerAdListener = this@BannerActivity
            moPubView?.loadAd()
        }
    }

    override fun onDestroy() {
        moPubView?.destroy()
        super.onDestroy()
    }

    override fun onBannerLoaded(moPubView: MoPubView) {
        Log.d("debug", "onBannerLoaded")
    }

    override fun onBackPressed() {
        Log.d("debug", "onBackPressed")
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
}
