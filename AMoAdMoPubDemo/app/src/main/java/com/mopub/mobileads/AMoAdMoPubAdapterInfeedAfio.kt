package com.mopub.mobileads

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amoad.AMoAdNativeListener
import com.amoad.AMoAdNativeViewManager
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader


open class AMoAdMoPubAdapterInfeedAfio : CustomEventBanner(), AMoAdNativeListener {

    override fun loadBanner(context: Context?, customEventBannerListener: CustomEventBannerListener?, localExtras: MutableMap<String, Any>?, serverExtras: MutableMap<String, String>?) {

        customEventBannerListener ?: return

        var customEventClassData = AMoAdMoPubUtil.extractCustomEventClassDataForInfeedAfio(serverExtras)
        customEventClassData ?: return

        val factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true
        val xpp = factory.newPullParser()
        xpp.setInput(StringReader(customEventClassData.file))

        var mAdView: View? = null
        val view = LayoutInflater.from(context).inflate(xpp, mAdView as ViewGroup)
        mAdView?.visibility = View.INVISIBLE

        Log.d("debug", "hoge : $view")

        // 広告準備
        AMoAdNativeViewManager.getInstance(context).prepareAd(customEventClassData.sid, true, true)
        // 広告取得
        AMoAdNativeViewManager.getInstance(context).renderAd(customEventClassData.sid, "", view, this)
    }

    override fun onInvalidate() {
    }

    override fun onReceived(s: String, s1: String, view: View, result: AMoAdNativeListener.Result) {
        Log.d("debug", "onReceived")
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