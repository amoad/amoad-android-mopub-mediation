package com.mopub.mobileads

import android.content.Context

class AMoAdMoPubUtil {

    companion object {

        fun extractCustomEventClassData(serverExtras: MutableMap<String, String>?): AMoAdCustomEventClassData? {

            serverExtras ?: return null
            val sid = serverExtras["sid"] ?: return null
            return AMoAdCustomEventClassData(sid)
        }

        fun getResourceId(resourceName: String, type: String, context: Context): Int {
            return context.resources.getIdentifier(resourceName, "layout", context.packageName)
        }
    }

}

class AMoAdCustomEventClassData(var sid: String)