package com.example.libsandrest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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

    // to register as a broadcast receiver for some service

    private inner class MyServiceReciever: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
        //To change body of created functions use File | Settings | File Templates.
            if(intent != null){
                Log.d("myservice", "AppEnd:: we got it!! ${intent.getStringExtra("fileLocation")}")

                startActivity(Intent(this@MainActivity, MartysPaintLib::class.java))
            }
        }
    }

    private val url_xkcd = "http://xkcd.com"
    private val url_xkcd_json = "info.0.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // deal if launched from a notification
        if( intent == null ){ //use savedInstanceState how??? << Not working
            Log.d("myservice", "Launched from notification intent")

        }else{
        // else init b/c is first time
            //ensure we've got permission to save files


            //register broadcast receiver with set filter
            val intentFilter = IntentFilter()
            intentFilter.addAction("downloadComplete")
            registerReceiver(MyServiceReciever(), intentFilter)
        }
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


    fun gamesPage(v: View){
        startActivity(Intent(this, GamesActivity::class.java))
    }

    fun startService(v: View){
        val intented = Intent(this, MyService::class.java)
        intented.putExtra("name", "THis is a test")
        intented.putExtra("value", 123456789)
        intented.action = "download"
        startService( intented )
    }
}
