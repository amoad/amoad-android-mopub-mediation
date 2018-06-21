package com.mopub.mobileads

import android.content.Context
import android.util.Log
import com.amoad.AMoAdView
import com.amoad.AdCallback

open class AMoAdMoPubAdapterBanner : CustomEventBanner() ,AdCallback {

    private var _amoadView: AMoAdView? = null
    private var _customEventBannerListener: CustomEventBannerListener? = null

    override fun loadBanner(context: Context?, customEventBannerListener: CustomEventBannerListener?, localExtras: MutableMap<String, Any>?, serverExtras: MutableMap<String, String>?) {

        _customEventBannerListener = customEventBannerListener
        _customEventBannerListener ?: return

        var customEventClassData = AMoAdMoPubUtil.extractCustomEventClassDataForDisplay(serverExtras)
        customEventClassData ?: return

        _amoadView = AMoAdView(context)
        _amoadView?.sid = customEventClassData.sid
        _amoadView?.setCallback(this)
    }

    override fun onInvalidate() {
    }

    override fun didReceiveAd() {
        //受信成功
        Log.d("debug", "didReceiveAd")
        _customEventBannerListener?.onBannerLoaded(_amoadView)
    }

    override fun didFailToReceiveAdWithError() {
        //受信失敗
        Log.d("debug", "didFailToReceiveAdWithError")
        _customEventBannerListener?.onBannerFailed(null)
    }

    override fun didReceiveEmptyAd() {
        //広告が配信されてない
        Log.d("debug", "didReceiveEmptyAd")
        _customEventBannerListener?.onBannerFailed(null)
    }
}