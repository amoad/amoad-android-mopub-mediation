package com.mopub.mobileads

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amoad.AMoAdNativeListener
import com.amoad.AMoAdNativeViewManager
import android.widget.LinearLayout
import com.amoad.AdResult


open class AMoAdMoPubAdapterInfeedAfio : CustomEventBanner(), AMoAdNativeListener {

    private var _customEventBannerListener: CustomEventBannerListener? = null

    override fun loadBanner(context: Context?, customEventBannerListener: CustomEventBannerListener?, localExtras: MutableMap<String, Any>?, serverExtras: MutableMap<String, String>?) {

        _customEventBannerListener = customEventBannerListener
        customEventBannerListener ?: return

        var customEventClassData = AMoAdMoPubUtil.extractCustomEventClassDataForInfeedAfio(serverExtras)
        customEventClassData ?: return

        val resId = AMoAdMoPubUtil.getResourceId(customEventClassData.file, "layout", context!!)
        val view = LayoutInflater.from(context).inflate(resId, LinearLayout(context) as ViewGroup)

        // 広告準備
        AMoAdNativeViewManager.getInstance(context).prepareAd(customEventClassData.sid, true, true)

        // 広告取得
        AMoAdNativeViewManager.getInstance(context).renderAd(customEventClassData.sid, "", view, this)
    }

    override fun onInvalidate() {
    }

    override fun onReceived(s: String, s1: String, view: View, result: AMoAdNativeListener.Result) {
        Log.d("debug", "onReceived")
        when (result) {
            AdResult.Success -> {
                Log.d("debug", "広告ロード成功")
                _customEventBannerListener?.onBannerLoaded(view)
            }
            AdResult.Empty -> {
                Log.d("debug", "配信する広告がない")
                _customEventBannerListener?.onBannerFailed(null)

            }
            AdResult.Failure -> {
                Log.d("debug", "広告ロード失敗")
                _customEventBannerListener?.onBannerFailed(null)
            }
        }
    }

    override fun onIconReceived(s: String, s1: String, view: View, result: AMoAdNativeListener.Result) {
        Log.d("debug", "onIconReceived")
    }

    override fun onImageReceived(s: String, s1: String, view: View, result: AMoAdNativeListener.Result) {
        // 広告ダウンロードが完了したら View を表示する
        Log.d("debug", "onImageReceived")
    }

    override fun onClicked(s: String, s1: String, view: View) {
        Log.d("debug", "onClicked")
    }
}