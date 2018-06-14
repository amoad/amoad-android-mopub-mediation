package com.mopub.mobileads

class AMoAdMoPubUtil {

    companion object {

        fun extractCustomEventClassData(serverExtras: MutableMap<String, String>?): AMoAdCustomEventClassData? {

            serverExtras ?: return null
            val sid = serverExtras["sid"] ?: return null
            return AMoAdCustomEventClassData(sid)
        }
    }

}

class AMoAdCustomEventClassData(var sid: String)