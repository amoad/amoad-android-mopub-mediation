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
        _interstitialListener = customEventInterstitialListener
        customEventInterstitialListener ?: return
        _interstitialAfioData = AMoAdMoPubUtil.extractInterstitialAfioData(serverExtras)
        val customEventClassData = _interstitialAfioData ?: return

        AMoAdInterstitialVideo.sharedInstance(_context, customEventClassData.sid, "")
        AMoAdInterstitialVideo.sharedInstance(_context, customEventClassData.sid, "").setListener(this)
        AMoAdInterstitialVideo.sharedInstance(_context, customEventClassData.sid, "").load(_context)
    }

    override fun showInterstitial() {

        if (AMoAdInterstitialVideo.sharedInstance(_context, _interstitialAfioData?.sid, "").isLoaded) {
            AMoAdInterstitialVideo.sharedInstance(_context, _interstitialAfioData?.sid, "").show(_context)
        }

        _interstitialListener?.onInterstitialShown()
    }

    override fun onInvalidate() {
    }

    override fun onLoad(amoadInterstitialVideo: AMoAdInterstitialVideo?, result: AdResult?) {

        when (result) {
            AdResult.Success -> {
                Log.d("debug", "広告ロード成功")
                _interstitialListener?.onInterstitialLoaded()
            }
            AdResult.Empty -> {
                Log.d("debug", "広告ロード失敗")
                _interstitialListener?.onInterstitialFailed(null)
            }
            AdResult.Failure -> {
                Log.d("debug", "配信する広告がない")
                _interstitialListener?.onInterstitialFailed(null)
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
        _interstitialListener?.onInterstitialDismissed()
    }

    override fun onFailed(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        // 動画の再生に失敗した
        Log.d("debug", "onFailed")
        _interstitialListener?.onInterstitialDismissed()
    }

    override fun onShown(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        // 広告を表示した
        Log.d("debug", "onShown")
        _interstitialListener?.onInterstitialDismissed()
    }

    override fun onDismissed(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        // 広告を閉じた
        Log.d("debug", "onDismissed")
        _interstitialListener?.onInterstitialDismissed()
    }

    override fun onClick(amoadInterstitialVideo: AMoAdInterstitialVideo?) {
        // 広告がクリックされた
        Log.d("debug", "onClick")
        _interstitialListener?.onInterstitialDismissed()
    }
}