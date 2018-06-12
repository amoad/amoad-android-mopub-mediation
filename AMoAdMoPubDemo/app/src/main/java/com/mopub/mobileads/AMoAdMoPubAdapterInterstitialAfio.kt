package com.mopub.mobileads

import android.content.Context
import android.util.Log

open class AMoAdMoPubAdapterInterstitialAfio : CustomEventInterstitial() {

    private var _customEventClassData: AMoAdCustomEventClassData? = null
    private var _customEventInterstitialListener: CustomEventInterstitialListener? = null
    private var _context: Context? = null

    override fun loadInterstitial(context: Context?, customEventInterstitialListener: CustomEventInterstitialListener?, localExtras: MutableMap<String, Any>?, serverExtras: MutableMap<String, String>?) {

        _context = context
        _customEventInterstitialListener = customEventInterstitialListener
        customEventInterstitialListener ?: return
        _customEventClassData = AMoAdMoPubUtil.extractCustomEventClassData(serverExtras)
        val customEventClassData = _customEventClassData ?: return
    }

        override fun showInterstitial() {

        val customEventClassData = _customEventClassData ?: return
        val customEventInterstitialListener = _customEventInterstitialListener ?: return
        val context = _context ?: return

        customEventInterstitialListener.onInterstitialShown()
    }

    override fun onInvalidate() {
    }
}