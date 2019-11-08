package com.example.widgetsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun radioButtonClick(view: View){
        var msg = when(view){
            radioButton2 -> "Snake"
            radioButton3 -> "Lizard"
            else -> "Reptile"
        }
        println(">>>>>>>> Found something! It's a $msg for selection ${view.id.toString()}")
    }


    fun buttonClick(view:View){
        startActivity( Intent( this@MainActivity, ListsActivity::class.java))
//        finish()
    }

    fun dataButtonClick(view:View){
        startActivity( Intent( this@MainActivity, DataStorageActivity::class.java))
//        finish()
    }

    fun dynamicUiButtonClick(v:View){

        startActivity( Intent( this@MainActivity, DynamicUIActivity::class.java))
    }
}
