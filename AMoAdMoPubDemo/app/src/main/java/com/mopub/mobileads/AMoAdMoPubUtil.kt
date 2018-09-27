package com.mopub.mobileads

class AMoAdMoPubUtil {

    companion object {
        fun extractSid(serverExtras: MutableMap<String, String>?): String? {
            serverExtras ?: return null
            val sid = serverExtras["sid"] ?: return null
            return sid
        }
    }
}
