package jp.co.cyberagent.amoad.amoadmopubdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import com.mopub.common.MoPub
import com.mopub.common.SdkConfiguration
import com.mopub.common.SdkInitializationListener

class MainActivity : AppCompatActivity() {

    private data class Item<T>(val title: String, val className: Class<T>)
    private var items = arrayListOf(
        Item("バナー広告", BannerActivity::class.java),
        Item("インターステイシャル広告", InterstitialActivity::class.java),
        Item("インフィード AfiO 広告", InfeedAfioActivity::class.java),
        Item("インターステイシャル AfiO 広告", InterstitialAfioActivity::class.java)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sdkConfiguration = SdkConfiguration.Builder("adUnitIdを指定してください")
//                .withMediationSettings("MEDIATION_SETTINGS")
//                .withNetworksToInit("NETWORKS")
                .build()
        MoPub.initializeSdk(this, sdkConfiguration, initSdkListener())
        initListView()
    }

    private fun initSdkListener(): SdkInitializationListener {
        return SdkInitializationListener {
            // MoPub SDK initialized.
            Log.d("debug","SDK initialization complete")
        }
    }

    private fun initListView() {
        val arrayAdapter = ArrayAdapter<Any>(this, android.R.layout.simple_list_item_1)
        for (item in items){ arrayAdapter.add(item.title) }
        listView.adapter = arrayAdapter
        listView.setOnItemClickListener {_, _, position, _ ->
            var intent = Intent(this, items[position].className)
            startActivity(intent)
        }
    }
}