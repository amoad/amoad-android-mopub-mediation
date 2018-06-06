package amoad.com.amoadmopubdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.amoad.AMoAdBuildConfig
import com.amoad.AMoAdLogger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val items = listOf("Banner")

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
                else -> {
                    intent = Intent(this, BannerActivity::class.java)
                }
            }
            startActivity(intent)
        }
    }
}