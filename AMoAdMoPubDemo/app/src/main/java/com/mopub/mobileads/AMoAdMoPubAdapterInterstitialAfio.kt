package com.mopub.mobileads

import android.content.Context
import android.util.Log
import com.amoad.AMoAdInterstitialVideo
import com.amoad.AdResult

open class AMoAdMoPubAdapterInterstitialAfio : CustomEventInterstitial(), AMoAdInterstitialVideo.Listener {

    private var _interstitialAfioData: InterstitialAfioData? = null
    private var _interstitialListener: CustomEventInterstitialListener? = null
    private var _context: Context? = null

    override fun loadInterstitial(context: Context?, customEventInterstitialListener: CustomEventInterstitialListener?, localExtras: MutableMap<String, Any>?, serverExtras: MutableMap<String, String>?) {

        _context = context
        _context ?: return
        _interstitialListener = customEventInterstitialListener
        _interstitialListener ?: return
        _interstitialAfioData = AMoAdMoPubUtil.extractInterstitialAfioData(serverExtras)
        _interstitialAfioData ?: return

        AMoAdInterstitialVideo.sharedInstance(_context, _interstitialAfioData?.sid, "").setListener(this)
        // 任意でpropertyの割り当てが可能です。
//        AMoAdInterstitialVideo.sharedInstance(_context, interstitialAfioData.sid, "").isCancellable = false
        AMoAdInterstitialVideo.sharedInstance(_context, _interstitialAfioData?.sid, "").load(_context)
    }

    override fun showInterstitial() {
        if (AMoAdInterstitialVideo.sharedInstance(_context, _interstitialAfioData?.sid, "").isLoaded) {
            AMoAdInterstitialVideo.sharedInstance(_context, _interstitialAfioData?.sid, "").show(_context)
        } else {
            Log.d("debug", "Interstitial Afio Ad wasn't loaded")
        }
    }

    override fun onLoad(amoadInterstitialVideo: AMoAdInterstitialVideo?, result: AdResult?) {

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

    override fun onStart(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        Log.d("debug", "動画の再生を開始した")
    }

    override fun onShown(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        Log.d("debug", "広告を表示した")
        _interstitialListener?.onInterstitialShown()
    }

    override fun onComplete(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        Log.d("debug", "動画を最後まで再生完了した")
    }

    override fun onFailed(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        Log.d("debug", "動画の再生に失敗した")
    }

    override fun onDismissed(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        Log.d("debug", "広告を閉じた")
        _interstitialListener?.onInterstitialDismissed()
    }

    override fun onClick(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        Log.d("debug", "広告がクリックされた")
        _interstitialListener?.onInterstitialClicked()
        _interstitialListener?.onLeaveApplication()
    }

    override fun onInvalidate() {
    }
}