package jp.co.cyberagent.amoad.amoadmopubdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubView
import kotlinx.android.synthetic.main.activity_banner.*

class BannerActivity : AppCompatActivity(), MoPubView.BannerAdListener {

    private var adUnitID = "管理画面から取得したAdUnitIDを指定してください"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        createAndLoadBannerView()
    }

    override fun onDestroy() {
        bannerView.destroy()
        super.onDestroy()
    }

    internal fun createAndLoadBannerView() {
        bannerView.adUnitId = adUnitID
        bannerView.bannerAdListener = this@BannerActivity
        bannerView.loadAd()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onBannerLoaded(moPubView: MoPubView) {}

    override fun onBannerFailed(moPubView: MoPubView, moPubErrorCode: MoPubErrorCode) {}

    override fun onBannerClicked(moPubView: MoPubView) {}

    override fun onBannerExpanded(moPubView: MoPubView) {}

    override fun onBannerCollapsed(moPubView: MoPubView) {}
}
