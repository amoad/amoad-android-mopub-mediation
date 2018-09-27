package com.mopub.mobileads

import android.content.Context
import android.util.Log
import com.amoad.AMoAdInterstitialVideo
import com.amoad.AdResult

open class AMoAdMoPubAdapterInterstitialAfio : CustomEventInterstitial(), AMoAdInterstitialVideo.Listener {

    private var _sid : String? = null
    private var _interstitialAfioListener: CustomEventInterstitialListener? = null
    private var _context: Context? = null

    override fun loadInterstitial(context: Context?, customEventInterstitialListener: CustomEventInterstitialListener?, localExtras: MutableMap<String, Any>?, serverExtras: MutableMap<String, String>?) {

        _context = context
        _context ?: return
        _interstitialAfioListener = customEventInterstitialListener
        _interstitialAfioListener ?: return
        _sid = AMoAdMoPubUtil.extractSid(serverExtras)
        _sid ?: return

        AMoAdInterstitialVideo.sharedInstance(_context, _sid, "").setListener(this)
        // 任意でpropertyの割り当てが可能です。
//        AMoAdInterstitialVideo.sharedInstance(_context, _sid, "").isCancellable = false
        AMoAdInterstitialVideo.sharedInstance(_context, _sid, "").load(_context)
    }

    override fun showInterstitial() {
        if (AMoAdInterstitialVideo.sharedInstance(_context, _sid, "").isLoaded) {
            AMoAdInterstitialVideo.sharedInstance(_context, _sid, "").show(_context)
        } else {
            Log.d("debug", "Interstitial Afio Ad wasn't loaded")
        }
    }

    override fun onLoad(amoadInterstitialVideo: AMoAdInterstitialVideo?, result: AdResult?) {

        when (result) {
            AdResult.Success -> {
                Log.d("debug", "広告ロード成功")
                _interstitialAfioListener?.onInterstitialLoaded()
            }
            AdResult.Empty -> {
                Log.d("debug", "配信する広告がない")
                _interstitialAfioListener?.onInterstitialFailed(null)
            }
            AdResult.Failure -> {
                Log.d("debug", "広告ロード失敗")
                _interstitialAfioListener?.onInterstitialFailed(null)
            }
        }
    }

    override fun onStart(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        Log.d("debug", "動画の再生を開始した")
    }

    override fun onShown(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        Log.d("debug", "広告を表示した")
        _interstitialAfioListener?.onInterstitialShown()
    }

    override fun onComplete(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        Log.d("debug", "動画を最後まで再生完了した")
    }

    override fun onFailed(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        Log.d("debug", "動画の再生に失敗した")
    }

    override fun onDismissed(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        Log.d("debug", "広告を閉じた")
        _interstitialAfioListener?.onInterstitialDismissed()
    }

    override fun onClick(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        Log.d("debug", "広告がクリックされた")
        _interstitialAfioListener?.onInterstitialClicked()
        _interstitialAfioListener?.onLeaveApplication()
    }

    override fun onInvalidate() {
    }
}