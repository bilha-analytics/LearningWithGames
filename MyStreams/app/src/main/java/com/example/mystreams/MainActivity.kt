package com.example.mystreams

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val t = Thread{
            Thread.sleep( 3000 )
            startActivity(Intent(this@MainActivity, ListMenuActivity::class.java))
            finish()
        }
        t.start()

//        Handler(Runnable {  })

    }


}