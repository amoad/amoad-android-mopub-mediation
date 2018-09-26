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

    companion object {
        private var sid: String? = null
        private fun saveSid(sid: String?) {
            AMoAdMoPubAdapterInterstitial.sid = sid
        }
        private fun deleteSid() {
            AMoAdMoPubAdapterInterstitial.sid = null
        }
        fun readSid() : String? {
            return AMoAdMoPubAdapterInterstitial.sid
        }
        fun closeInterstitial(sid: String?) {
            InterstitialAd.close(sid)
            AMoAdMoPubAdapterInterstitial.deleteSid()
        }
    }

    override fun loadInterstitial(context: Context?, customEventInterstitialListener: CustomEventInterstitialListener?, localExtras: MutableMap<String, Any>?, serverExtras: MutableMap<String, String>?) {

        _context = context
        _context ?: return
        _interstitialListener = customEventInterstitialListener
        _interstitialListener ?: return
        _interstitialData = AMoAdMoPubUtil.extractInterstitialData(serverExtras)
        _interstitialData ?: return

        InterstitialAd.register(_interstitialData?.sid)
//        InterstitialAd.setAutoReload(_interstitialData?.sid, true) // 任意でpropertyの割り当てが可能です。
        InterstitialAd.load(_context, _interstitialData?.sid) { sid, result, error ->
            when (result) {
                AdResult.Success -> {
                    Log.d("debug", "広告ロード成功")
                    _interstitialListener?.onInterstitialLoaded()
                }
                AdResult.Empty -> {
                    Log.d("debug", "配信する広告がない")
                    _interstitialListener?.onInterstitialFailed(null)
                }
                AdResult.Failure -> {
                    Log.d("debug", "広告ロード失敗")
                    _interstitialListener?.onInterstitialFailed(null)
                }
            }
        }
    }

    override fun showInterstitial() {

        if (InterstitialAd.isLoaded(_interstitialData?.sid)) {

            _interstitialListener?.onInterstitialShown()

            // close用にstaticでsidを保持
            _interstitialData?.sid.let { AMoAdMoPubAdapterInterstitial.saveSid(it) }

            InterstitialAd.show(_context as Activity?, _interstitialData?.sid) { result ->

                when (result) {
                    InterstitialAd.Result.Click -> {
                        Log.d("debug", "リンクボタンがクリックされたので閉じました")
                        _interstitialListener?.onInterstitialClicked()
                        _interstitialListener?.onLeaveApplication()
                        _interstitialListener?.onInterstitialDismissed()
                    }
                    InterstitialAd.Result.Failure -> {
                        Log.d("debug", "広告の取得に失敗しました")
                        _interstitialListener?.onInterstitialFailed(null)
                    }
                    InterstitialAd.Result.Duplicated -> {
                        Log.d("debug", "既に開かれているので開きませんでした")
                    }
                    InterstitialAd.Result.CloseFromApp -> {
                        Log.d("debug", "アプリから閉じられました")
                        _interstitialListener?.onInterstitialDismissed()
                    }
                    InterstitialAd.Result.Close -> {
                        Log.d("debug", "閉じるボタンがクリックされたので閉じました")
                        _interstitialListener?.onInterstitialDismissed()
                    }
                }
            }
        } else {
            Log.d("debug", "Interstitial Ad wasn't loaded")
        }
    }

    override fun onInvalidate() {
    }
}