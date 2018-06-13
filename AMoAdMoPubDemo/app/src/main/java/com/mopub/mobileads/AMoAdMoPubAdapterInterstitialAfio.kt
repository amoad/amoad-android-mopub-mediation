package com.mopub.mobileads

import android.content.Context
import android.util.Log
import com.amoad.AMoAdInterstitialVideo
import com.amoad.AdResult

open class AMoAdMoPubAdapterInterstitialAfio : CustomEventInterstitial(), AMoAdInterstitialVideo.Listener {

    private var _customEventClassData: AMoAdCustomEventClassData? = null
    private var _customEventInterstitialListener: CustomEventInterstitialListener? = null
    private var _context: Context? = null
    var amoadInterstitialVideo:AMoAdInterstitialVideo? = null

    override fun loadInterstitial(context: Context?, customEventInterstitialListener: CustomEventInterstitialListener?, localExtras: MutableMap<String, Any>?, serverExtras: MutableMap<String, String>?) {

        _context = context
        _customEventInterstitialListener = customEventInterstitialListener
        customEventInterstitialListener ?: return
        _customEventClassData = AMoAdMoPubUtil.extractCustomEventClassData(serverExtras)
        val customEventClassData = _customEventClassData ?: return

        amoadInterstitialVideo = AMoAdInterstitialVideo.sharedInstance(_context, customEventClassData.sid, "")
        amoadInterstitialVideo?.setListener(this)
        amoadInterstitialVideo?.load(_context)
    }

    override fun showInterstitial() {

        val customEventClassData = _customEventClassData ?: return
        val customEventInterstitialListener = _customEventInterstitialListener ?: return
        val context = _context ?: return

        if (amoadInterstitialVideo != null) {
            if (amoadInterstitialVideo!!.isLoaded) {
                amoadInterstitialVideo?.show(context)
            }
        }

        customEventInterstitialListener.onInterstitialShown()
    }

    override fun onInvalidate() {
    }

    override fun onLoad(amoadInterstitialVideo: AMoAdInterstitialVideo?, result: AdResult?) {

        when (result) {
            AdResult.Success -> {
                Log.d("debug", "広告ロード成功")
                _customEventInterstitialListener?.onInterstitialLoaded()
            }
            AdResult.Empty -> {
                Log.d("debug", "広告ロード失敗")
                _customEventInterstitialListener?.onInterstitialFailed(null)
            }
            AdResult.Failure -> {
                Log.d("debug", "配信する広告がない")
                _customEventInterstitialListener?.onInterstitialFailed(null)
            }
        }
    }

    override fun onStart(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        // 動画の再生を開始した
        Log.d("debug", "onStart")
    }

    override fun onComplete(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        // 動画を最後まで再生完了した
        Log.d("debug", "onComplete")
        _customEventInterstitialListener?.onInterstitialDismissed()
    }

    override fun onFailed(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        // 動画の再生に失敗した
        Log.d("debug", "onFailed")
        _customEventInterstitialListener?.onInterstitialDismissed()
    }

    override fun onShown(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        // 広告を表示した
        Log.d("debug", "onShown")
        _customEventInterstitialListener?.onInterstitialDismissed()
    }

    override fun onDismissed(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        // 広告を閉じた
        Log.d("debug", "onDismissed")
        _customEventInterstitialListener?.onInterstitialDismissed()
    }

    override fun onClick(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        // 広告がクリックされた
        Log.d("debug", "onClick")
        _customEventInterstitialListener?.onInterstitialDismissed()
    }
}