package com.mopub.mobileads

class AMoAdMoPubUtil {

    companion object {

        fun extractCustomEventClassDataForDisplay(serverExtras: MutableMap<String, String>?): AMoAdCustomEventClassDataForDisplay? {
            serverExtras ?: return null
            val sid = serverExtras["sid"] ?: return null
            return AMoAdCustomEventClassDataForDisplay(sid)
        }

        fun extractCustomEventClassDataForInfeedAfio(serverExtras: MutableMap<String, String>?): AMoAdCustomEventClassDataForInfeedAfio? {
            serverExtras ?: return null
            val sid = serverExtras["sid"] ?: return null
            val file = serverExtras["file"] ?: return null
            return AMoAdCustomEventClassDataForInfeedAfio(sid,file)
        }
    }
}

class AMoAdCustomEventClassDataForDisplay(var sid: String)
class AMoAdCustomEventClassDataForInfeedAfio(var sid: String,
                                             var file: String)