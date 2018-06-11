package com.mopub.mobileads

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.amoad.AMoAdView
import com.amoad.AdCallback



open class AMoAdMoPubAdapterBanner() : CustomEventBanner() {

    override fun loadBanner(context: Context?, customEventBannerListener: CustomEventBannerListener?, localExtras: MutableMap<String, Any>?, serverExtras: MutableMap<String, String>?) {

        customEventBannerListener ?: return

        var customEventClassData = AMoAdMoPubUtil.extractCustomEventClassData(serverExtras)
        customEventClassData ?: return

        val mAdView = AMoAdView(context)
        mAdView?.setSid(customEventClassData.sid)
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

    constructor(parcel: Parcel) : this() {
    }

    companion object CREATOR : Parcelable.Creator<AMoAdMoPubAdapterBanner> {
        override fun createFromParcel(parcel: Parcel): AMoAdMoPubAdapterBanner {
            return AMoAdMoPubAdapterBanner(parcel)
        }

        override fun newArray(size: Int): Array<AMoAdMoPubAdapterBanner?> {
            return arrayOfNulls(size)
        }
    }
}