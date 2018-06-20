package com.mopub.mobileads

import android.content.Context
import android.util.Log
import com.amoad.AMoAdInterstitialVideo
import com.amoad.AdResult

open class AMoAdMoPubAdapterInterstitialAfio : CustomEventInterstitial(), AMoAdInterstitialVideo.Listener {

    private var _customEventClassData: AMoAdCustomEventClassDataForDisplay? = null
    private var _customEventInterstitialListener: CustomEventInterstitialListener? = null
    private var _context: Context? = null

    override fun loadInterstitial(context: Context?, customEventInterstitialListener: CustomEventInterstitialListener?, localExtras: MutableMap<String, Any>?, serverExtras: MutableMap<String, String>?) {

        _context = context
        _customEventInterstitialListener = customEventInterstitialListener
        customEventInterstitialListener ?: return
        _customEventClassData = AMoAdMoPubUtil.extractCustomEventClassDataForDisplay(serverExtras)
        val customEventClassData = _customEventClassData ?: return

        AMoAdInterstitialVideo.sharedInstance(_context, customEventClassData.sid, "")
        AMoAdInterstitialVideo.sharedInstance(_context, customEventClassData.sid, "").setListener(this)
        AMoAdInterstitialVideo.sharedInstance(_context, customEventClassData.sid, "").load(_context)
    }

    override fun showInterstitial() {

        if (AMoAdInterstitialVideo.sharedInstance(_context, _customEventClassData?.sid, "").isLoaded) {
            AMoAdInterstitialVideo.sharedInstance(_context, _customEventClassData?.sid, "").show(_context)
        }

        _customEventInterstitialListener?.onInterstitialShown()
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