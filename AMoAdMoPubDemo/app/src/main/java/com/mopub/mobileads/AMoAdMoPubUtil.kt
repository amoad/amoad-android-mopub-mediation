package com.mopub.mobileads

import android.content.Context

class AMoAdMoPubUtil {

    companion object {

        fun extractBannerData(serverExtras: MutableMap<String, String>?): BannerData? {
            serverExtras ?: return null
            val sid = serverExtras["sid"] ?: return null
            return BannerData(sid)
        }

        fun extractInterstitialData(serverExtras: MutableMap<String, String>?): InterstitialData? {
            serverExtras ?: return null
            val sid = serverExtras["sid"] ?: return null
            return InterstitialData(sid)
        }

        fun extractInfeedAfioData(serverExtras: MutableMap<String, String>?): InfeedAfioData? {
            serverExtras ?: return null
            val sid = serverExtras["sid"] ?: return null
            val file = serverExtras["file"] ?: return null
            return InfeedAfioData(sid,file)
        }

        fun extractInterstitialAfioData(serverExtras: MutableMap<String, String>?): InterstitialAfioData? {
            serverExtras ?: return null
            val sid = serverExtras["sid"] ?: return null
            return InterstitialAfioData(sid)
        }

        fun getResourceId(resourceName: String, type: String, context: Context): Int {
            return context.resources.getIdentifier(resourceName, "layout", context.packageName)
        }
    }
}

class BannerData(var sid: String)
class InterstitialData(var sid: String)
class InfeedAfioData(var sid: String, var file: String)
class InterstitialAfioData(var sid: String)
