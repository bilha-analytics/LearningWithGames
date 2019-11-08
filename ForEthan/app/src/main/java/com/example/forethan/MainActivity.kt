package com.example.forethan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timer("loadingSplash", true).schedule(5000) {
            showHomeScreen()
        }
    }


    private fun showHomeScreen(){
        startActivity(Intent(this, HomeActivity::class.java) )
        finish()
    }
}
