package jp.co.cyberagent.amoad.amoadmopubdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.amoad.AMoAdBuildConfig
import com.amoad.AMoAdLogger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    enum class AdType(val rawValue: Int)  {
        BANNER(0),
        INTERSTITIAL(1),
        INFEEDAFIO(2),
        INTERSTITIALAFIO(3)
    }

    private val items = listOf(
            "バナー広告",
            "インターステイシャル広告",
            "インフィード AfiO 広告",
            "インターステイシャル AfiO 広告"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AMoAdLogger.getInstance().setEnabled(true)
        AMoAdBuildConfig.toStaging()

        listView.adapter = ArrayAdapter<Any>(this, android.R.layout.simple_list_item_1, items)
        listView.setOnItemClickListener {parent, view, position, id ->

            var intent: Intent? = null

            when (position) {
                AdType.BANNER.rawValue -> {
                    intent = Intent(this, BannerActivity::class.java)
                }
                AdType.INTERSTITIAL.rawValue -> {
                    intent = Intent(this, InterstitialActivity::class.java)
                }
                AdType.INFEEDAFIO.rawValue -> {
                    intent = Intent(this, InfeedAfioActivity::class.java)
                }
                AdType.INTERSTITIALAFIO.rawValue -> {
                    intent = Intent(this, InterstitialAfioActivity::class.java)
                }
            }
            startActivity(intent)
        }
    }
}