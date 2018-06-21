package com.mopub.mobileads

import android.app.Activity
import android.content.Context
import android.util.Log
import com.amoad.AdResult
import com.amoad.InterstitialAd

open class AMoAdMoPubAdapterInterstitial : CustomEventInterstitial() {

    private var _customEventClassData: AMoAdCustomEventClassDataForDisplay? = null
    private var _customEventInterstitialListener: CustomEventInterstitialListener? = null
    private var _context: Context? = null

    override fun loadInterstitial(context: Context?, customEventInterstitialListener: CustomEventInterstitialListener?, localExtras: MutableMap<String, Any>?, serverExtras: MutableMap<String, String>?) {

        _context = context
        _customEventInterstitialListener = customEventInterstitialListener
        customEventInterstitialListener ?: return
        _customEventClassData = AMoAdMoPubUtil.extractCustomEventClassDataForDisplay(serverExtras)
        val customEventClassData = _customEventClassData ?: return

        InterstitialAd.register(customEventClassData.sid)
        InterstitialAd.setAutoReload(customEventClassData.sid, true);
        InterstitialAd.load(_context, customEventClassData.sid) { sid, result, error ->
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

        if (InterstitialAd.isLoaded(_customEventClassData?.sid)) {

            InterstitialAd.show(_context as Activity?, _customEventClassData?.sid) { result ->

                _customEventInterstitialListener?.onInterstitialShown()

                when (result) {
                    InterstitialAd.Result.Click -> {
                        Log.d("debug", "Click")
                        _customEventInterstitialListener?.onInterstitialDismissed()
                    }
                    InterstitialAd.Result.Failure -> {
                        Log.d("debug", "Failure")
                        _customEventInterstitialListener?.onInterstitialDismissed()
                    }
                    InterstitialAd.Result.Duplicated -> {
                        Log.d("debug", "Duplicated")
                        _customEventInterstitialListener?.onInterstitialDismissed()
                    }
                    InterstitialAd.Result.CloseFromApp -> {
                        Log.d("debug", "CloseFromApp")
                        _customEventInterstitialListener?.onInterstitialDismissed()
                    }
                    InterstitialAd.Result.Close -> {
                        Log.d("debug", "Close")
                        _customEventInterstitialListener?.onInterstitialDismissed()
                    }
                }
            }
        }
    }

    override fun onInvalidate() {
    }
}