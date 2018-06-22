package jp.co.cyberagent.amoad.amoadmopubdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.amoad.AMoAdBuildConfig
import com.amoad.AMoAdLogger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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

        var adapter = ArrayAdapter<Any>(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        // Click action
        listView.setOnItemClickListener {parent, view, position, id ->
            val intent: Intent?
            when (position) {
                0 -> {
                    intent = Intent(this, BannerActivity::class.java)
                }
                1 -> {
                    intent = Intent(this, InterstitialActivity::class.java)
                }
                2 -> {
                    intent = Intent(this, InfeedAfioActivity::class.java)
                }
                3 -> {
                    intent = Intent(this, InterstitialAfioActivity::class.java)
                }
                else -> {
                    intent = Intent(this, BannerActivity::class.java)
                }
            }
            startActivity(intent)
        }
    }
}