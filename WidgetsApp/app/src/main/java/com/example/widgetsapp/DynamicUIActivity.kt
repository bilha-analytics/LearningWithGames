package com.example.widgetsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
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
            }
            grid_dynamic.addView(v)
            Log.d("inflater", "Inflating flag # $i")
        }
    }

}
