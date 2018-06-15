package com.mopub.mobileads

import android.content.Context
import android.util.Log
import com.amoad.AMoAdView
import com.amoad.AdCallback

open class AMoAdMoPubAdapterBanner : CustomEventBanner() {

    override fun loadBanner(context: Context?, customEventBannerListener: CustomEventBannerListener?, localExtras: MutableMap<String, Any>?, serverExtras: MutableMap<String, String>?) {

        customEventBannerListener ?: return

        var customEventClassData = AMoAdMoPubUtil.extractCustomEventClassDataForDisplay(serverExtras)
        customEventClassData ?: return

        val mAdView = AMoAdView(context)
        mAdView?.sid = customEventClassData.sid
        mAdView?.setCallback(object : AdCallback {
            override fun didReceiveAd() {
                //受信成功
                Log.d("debug", "didReceiveAd")
                customEventBannerListener.onBannerLoaded(mAdView)
            }

            override fun didFailToReceiveAdWithError() {
                //受信失敗
                Log.d("debug", "didFailToReceiveAdWithError")
                customEventBannerListener.onBannerFailed(null)
            }

            override fun didReceiveEmptyAd() {
                //広告が配信されてない
                Log.d("debug", "didReceiveEmptyAd")
                customEventBannerListener.onBannerFailed(null)
            }
        })
    }

    override fun onInvalidate() {
    }
}