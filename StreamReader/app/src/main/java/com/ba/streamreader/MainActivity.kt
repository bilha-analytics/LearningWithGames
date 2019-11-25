package com.ba.streamreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var makeupList_JSONObject: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Ion.with(this)
            .load( "https://makeup-api.herokuapp.com/api/v1/products.json" )
            .asString()
            .setCallback() { e, res ->
                makeupList_JSONObject = "{ \"obj\" : $res }"

                startActivity(Intent(this@MainActivity, HomeMenuActivity::class.java))

                finish()
            }

//        Thread( Runnable {
////            Thread.sleep( 5000 )
//            startActivity(Intent(this@MainActivity, HomeMenuActivity::class.java))
//            finish()
//        }).start()

    }
}
