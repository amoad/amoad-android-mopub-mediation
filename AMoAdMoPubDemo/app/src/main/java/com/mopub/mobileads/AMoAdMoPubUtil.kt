package com.mopub.mobileads

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
            return InfeedAfioData(sid)
        }

        fun extractInterstitialAfioData(serverExtras: MutableMap<String, String>?): InterstitialAfioData? {
            serverExtras ?: return null
            val sid = serverExtras["sid"] ?: return null
            return InterstitialAfioData(sid)
        }
    }
}

class BannerData(var sid: String)
class InterstitialData(var sid: String)
class InfeedAfioData(var sid: String)
class InterstitialAfioData(var sid: String)
