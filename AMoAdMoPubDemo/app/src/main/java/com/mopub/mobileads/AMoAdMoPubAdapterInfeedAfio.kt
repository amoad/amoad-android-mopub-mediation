package com.mopub.mobileads

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amoad.AMoAdNativeListener
import com.amoad.AMoAdNativeViewManager
import android.widget.LinearLayout
import com.amoad.AdResult


open class AMoAdMoPubAdapterInfeedAfio : CustomEventBanner(), AMoAdNativeListener {

    private var _bannerListener: CustomEventBannerListener? = null

    override fun loadBanner(context: Context?, customEventBannerListener: CustomEventBannerListener?, localExtras: MutableMap<String, Any>?, serverExtras: MutableMap<String, String>?) {

        _bannerListener = customEventBannerListener
        customEventBannerListener ?: return

        var infeedAfioData = AMoAdMoPubUtil.extractInfeedAfioData(serverExtras)
        infeedAfioData ?: return

        val resId = AMoAdMoPubUtil.getResourceId(infeedAfioData.file, "layout", context!!)
        val view = LayoutInflater.from(context).inflate(resId, LinearLayout(context) as ViewGroup)
        view?.visibility = View.INVISIBLE

        // 広告準備
        AMoAdNativeViewManager.getInstance(context).prepareAd(infeedAfioData.sid, true, true)

        // 広告取得
        AMoAdNativeViewManager.getInstance(context).renderAd(infeedAfioData.sid, "", view, this)

        _bannerListener?.onBannerLoaded(view)
    }

    override fun onInvalidate() {
    }

    override fun onReceived(s: String, s1: String, view: View, result: AMoAdNativeListener.Result) {
        Log.d("debug", "onReceived")
        when (result) {
            AdResult.Success -> {
                Log.d("debug", "広告ロード成功")
            }
            AdResult.Empty -> {
                Log.d("debug", "配信する広告がない")
                _bannerListener?.onBannerFailed(null)

            }
            AdResult.Failure -> {
                Log.d("debug", "広告ロード失敗")
                _bannerListener?.onBannerFailed(null)
            }
        }
    }

    override fun onIconReceived(s: String, s1: String, view: View, result: AMoAdNativeListener.Result) {
        Log.d("debug", "onIconReceived")
    }

    override fun onImageReceived(s: String, s1: String, view: View, result: AMoAdNativeListener.Result) {
        Log.d("debug", "onImageReceived")
        view?.visibility = View.VISIBLE
    }

    override fun onClicked(s: String, s1: String, view: View) {
        Log.d("debug", "onClicked")
    }
}