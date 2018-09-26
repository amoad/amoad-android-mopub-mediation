package jp.co.cyberagent.amoad.amoadmopubdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.mopub.mobileads.AMoAdMoPubAdapterInfeedAfio.Companion.extrasKey
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubView
import kotlinx.android.synthetic.main.activity_infeed_afio.*

class InfeedAfioActivity : AppCompatActivity(), MoPubView.BannerAdListener {

    private var adUnitID = "管理画面から取得したAdUnitIDを指定してください"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infeed_afio)
        createAndLoadInfeedAfio()
    }

    override fun onDestroy() {
        infeedAfio.destroy()
        super.onDestroy()
    }

    internal fun createAndLoadInfeedAfio() {

        infeedAfio.adUnitId = adUnitID
        infeedAfio.bannerAdListener = this@InfeedAfioActivity

        val view = LayoutInflater.from(this).inflate(R.layout.item_afio, LinearLayout(this) as ViewGroup)

        val extras = mapOf(extrasKey to view)
        infeedAfio.localExtras = extras
        infeedAfio.loadAd()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onBannerLoaded(view: MoPubView) {}

    override fun onBannerFailed(moPubView: MoPubView, moPubErrorCode: MoPubErrorCode) {}

    override fun onBannerClicked(moPubView: MoPubView) {}

    override fun onBannerExpanded(moPubView: MoPubView) {}

    override fun onBannerCollapsed(moPubView: MoPubView) {}
}
