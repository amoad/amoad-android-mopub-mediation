package com.mopub.mobileads

import android.app.Activity
import android.content.Context
import android.util.Log
import com.amoad.AdResult
import com.amoad.InterstitialAd

open class AMoAdMoPubAdapterInterstitial : CustomEventInterstitial() {

    private var _interstitialData: InterstitialData? = null
    private var _interstitialListener: CustomEventInterstitialListener? = null
    private var _context: Context? = null

    override fun loadInterstitial(context: Context?, customEventInterstitialListener: CustomEventInterstitialListener?, localExtras: MutableMap<String, Any>?, serverExtras: MutableMap<String, String>?) {

        _context = context
        _interstitialListener = customEventInterstitialListener
        customEventInterstitialListener ?: return
        _interstitialData = AMoAdMoPubUtil.extractInterstitialData(serverExtras)
        val interstitialData = _interstitialData ?: return

        InterstitialAd.register(interstitialData.sid)
        InterstitialAd.setAutoReload(interstitialData.sid, true)
        InterstitialAd.load(_context, interstitialData.sid) { sid, result, error ->
            when (result) {
                AdResult.Success -> {
                    Log.d("debug", "広告ロード成功")
                    customEventInterstitialListener.onInterstitialLoaded()
                }
                AdResult.Empty -> {
                    Log.d("debug", "広告ロード失敗")
                    customEventInterstitialListener.onInterstitialFailed(null)
                }
                AdResult.Failure -> {
                    Log.d("debug", "配信する広告がない")
                    customEventInterstitialListener.onInterstitialFailed(null)
                }
            }
        }
    }

    override fun showInterstitial() {

        if (InterstitialAd.isLoaded(_interstitialData?.sid)) {

            InterstitialAd.show(_context as Activity?, _interstitialData?.sid) { result ->

                _interstitialListener?.onInterstitialShown()

                when (result) {
                    InterstitialAd.Result.Click -> {
                        Log.d("debug", "Click")
                        _interstitialListener?.onInterstitialDismissed()
                    }
                    InterstitialAd.Result.Failure -> {
                        Log.d("debug", "Failure")
                        _interstitialListener?.onInterstitialDismissed()
                    }
                    InterstitialAd.Result.Duplicated -> {
                        Log.d("debug", "Duplicated")
                        _interstitialListener?.onInterstitialDismissed()
                    }
                    InterstitialAd.Result.CloseFromApp -> {
                        Log.d("debug", "CloseFromApp")
                        _interstitialListener?.onInterstitialDismissed()
                    }
                    InterstitialAd.Result.Close -> {
                        Log.d("debug", "Close")
                        _interstitialListener?.onInterstitialDismissed()
                    }
                }
            }
        }
    }

    override fun onInvalidate() {
    }
}