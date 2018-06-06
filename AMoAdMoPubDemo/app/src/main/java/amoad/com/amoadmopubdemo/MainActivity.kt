package amoad.com.amoadmopubdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.amoad.AMoAdBuildConfig
import com.amoad.AMoAdLogger

class MainActivity : AppCompatActivity() {

//    private val ITEMS : ArrayList<Array<Any>> = arrayOf(
//            "Banner広告", BannerActivity::class.java
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AMoAdLogger.getInstance().setEnabled(true)
        AMoAdBuildConfig.toStaging()

        initList()
    }

    private fun initList(){
//        ITEMS.add(arrayOf("Banner広告", BannerActivity::class.java))
    }
}
