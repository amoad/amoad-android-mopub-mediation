package jp.co.cyberagent.amoad.amoadmopubdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.mopub.mobileads.AMoAdMoPubAdapterInfeedAfio.Companion.extrasKey
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubView
import kotlinx.android.synthetic.main.activity_infeed_afio.*

class InfeedAfioActivity : AppCompatActivity(), MoPubView.BannerAdListener {

    private var adUnitID = "c3cba3cd32cd48d9a98d78c40aa8dd1d"

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

    override fun onBannerLoaded(view: MoPubView) {
        Log.d("debug", "onBannerLoaded")
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
