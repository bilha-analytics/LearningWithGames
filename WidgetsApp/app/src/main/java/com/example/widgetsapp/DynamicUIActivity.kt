package com.example.widgetsapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_dynamic_ui.*
import kotlinx.android.synthetic.main.flag.view.*

class DynamicUIActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_ui)

        for (i in 0..10 ) {
            val v = layoutInflater.inflate(R.layout.flag, null) // set ViewGroup to null at init so tha edits can reflect
//            v.findViewById<TextView>(R.id.txt_label).text = "I am # $i"
            v.txt_label.text = "I am # $i"

            v.img_icon.setOnClickListener{
                Log.d("Clicked", " item $i")
                Toast.makeText(this, "Clicked item $i", Toast.LENGTH_SHORT).show()

                // pop up dialog box
                showInfoDialog(i)

                //media player, tts
//                val mp = MediaPlayer.create(this, android.R.anim)
//                mp.start()

                //tts

            }

            grid_dynamic.addView(v)
            Log.d("inflater", "Inflating flag # $i")
        }
    }

    private fun showInfoDialog(i: Int) {
        val bld = AlertDialog.Builder(this)
        bld.setTitle("Game Over!")
        bld.setMessage("Time is up! Your score is $i")
        val q = arrayOf("The", "Quick", "Brown", "Fox")
        bld.setItems( q ){dialog, index ->
        }
        bld.setPositiveButton("Awesome!"){_, _ ->
            //e.g save stuff or something before clsoing the dialog
        }
        bld.create().show()
    }

}
