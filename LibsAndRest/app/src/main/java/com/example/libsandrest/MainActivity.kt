package com.example.libsandrest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val url_xkcd = "http://xkcd.com"
    private val url_xkcd_json = "info.0.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickFetch(v: View){
        Ion.with(this)
            .load( "$url_xkcd/${Random.nextInt(2226) }/$url_xkcd_json" )
            .asString()
            .setCallback(){ e, res ->
                updateImageView( res )
            }

    }

    fun updateImageView(res: String){
        val json = JSONObject(res)
        textView2.text = ""+json.getInt("day")+"-"+json.getInt("month")+"-"+json.getInt("year")
        Picasso.get().load(json.getString("img")).into( imageView )
        textView.text = json.getString( "alt" )
    }

    fun martysDrawLib(v: View){
        startActivity(Intent(this, MartysPaintLib::class.java))
    }
}
