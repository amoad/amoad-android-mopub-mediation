package com.mopub.mobileads

import android.content.Context
import android.util.Log
import android.view.View
import com.amoad.AMoAdNativeListener
import com.amoad.AMoAdNativeViewManager
import com.amoad.AdResult

open class AMoAdMoPubAdapterInfeedAfio : CustomEventBanner(), AMoAdNativeListener {

    private var _infeedAfioListener: CustomEventBannerListener? = null

    companion object {
        const val extrasKey = "adView"
    }

    override fun loadBanner(context: Context?, customEventBannerListener: CustomEventBannerListener?, localExtras: MutableMap<String, Any>?, serverExtras: MutableMap<String, String>?) {

        _infeedAfioListener = customEventBannerListener
        _infeedAfioListener ?: return

        var sid = AMoAdMoPubUtil.extractSid(serverExtras)
        sid ?: return

        var view: View? = localExtras?.get(extrasKey) as View
        view ?: return
        view?.visibility = View.INVISIBLE

        // 広告準備・取得
        AMoAdNativeViewManager.getInstance(context).prepareAd(sid, true, true)
        AMoAdNativeViewManager.getInstance(context).renderAd(sid, "", view, this)

    }

    override fun onReceived(s: String, s1: String, view: View, result: AMoAdNativeListener.Result) {
        when (result) {
            AdResult.Success -> {
                Log.d("debug", "広告ロード成功")
                _infeedAfioListener?.onBannerLoaded(view)
            }
            AdResult.Empty -> {
                Log.d("debug", "配信する広告がない")
                _infeedAfioListener?.onBannerFailed(null)

            }
            AdResult.Failure -> {
                Log.d("debug", "広告ロード失敗")
                _infeedAfioListener?.onBannerFailed(null)
            }
        }
    }

    override fun onIconReceived(s: String, s1: String, view: View, result: AMoAdNativeListener.Result) {
        when (result) {
            AdResult.Success -> {
                Log.d("debug", "アイコン取得成功")
            }
            AdResult.Empty -> {
                Log.d("debug", "配信するアイコンがない")
            }
            AdResult.Failure -> {
                Log.d("debug", "アイコン取得失敗")
            }
        }
    }

    override fun onImageReceived(s: String, s1: String, view: View, result: AMoAdNativeListener.Result) {
        when (result) {
            AdResult.Success -> {
                Log.d("debug", "メイン画像取得成功")
                view?.visibility = View.VISIBLE
            }
            AdResult.Empty -> {
                Log.d("debug", "配信するメイン画像がない")

            }
            AdResult.Failure -> {
                Log.d("debug", "メイン画像取得失敗")
            }
        }
    }

    override fun onClicked(s: String, s1: String, view: View) {
        Log.d("debug", "広告クリック")
        _infeedAfioListener?.onBannerClicked()
        _infeedAfioListener?.onLeaveApplication()
    }

    override fun onInvalidate() {
    }
}