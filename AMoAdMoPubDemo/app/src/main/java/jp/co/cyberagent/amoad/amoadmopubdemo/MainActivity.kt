package jp.co.cyberagent.amoad.amoadmopubdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.amoad.AMoAdBuildConfig
import com.amoad.AMoAdLogger
import kotlinx.android.synthetic.main.activity_main.*

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
        initListView()
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