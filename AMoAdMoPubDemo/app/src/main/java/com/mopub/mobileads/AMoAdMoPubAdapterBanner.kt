package com.mopub.mobileads

import android.content.Context
import android.util.Log
import com.amoad.AMoAdView
import com.amoad.AdCallback

open class AMoAdMoPubAdapterBanner : CustomEventBanner() ,AdCallback {

    private lateinit var _amoadView: AMoAdView
    private var _bannerListener: CustomEventBannerListener? = null

    override fun loadBanner(context: Context?, customEventBannerListener: CustomEventBannerListener?, localExtras: MutableMap<String, Any>?, serverExtras: MutableMap<String, String>?) {

        _bannerListener = customEventBannerListener
        _bannerListener ?: return

        var bannerData = AMoAdMoPubUtil.extractBannerData(serverExtras)
        bannerData ?: return

        _amoadView = AMoAdView(context)
        _amoadView.sid = bannerData.sid

        // 任意で各propertyの割り当てが可能です。
//        _amoadView.setClickTransition(AMoAdView.ClickTransition.JUMP)
//        _amoadView.setRotateTransition(AMoAdView.RotateTransition.ROTATE)
//        _amoadView.setResponsiveStyle(true)

        _amoadView?.setCallback(this)
    }

    override fun didReceiveAd() {
        Log.d("debug", "受信成功")
        _bannerListener?.onBannerLoaded(_amoadView)
    }

    override fun didFailToReceiveAdWithError() {
        Log.d("debug", "受信失敗")
        _bannerListener?.onBannerFailed(null)
    }

    override fun didReceiveEmptyAd() {
        Log.d("debug", "広告が配信されてない")
        _bannerListener?.onBannerFailed(null)
    }

    override fun onInvalidate() {
    }
}