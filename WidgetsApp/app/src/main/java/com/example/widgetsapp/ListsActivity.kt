package com.example.widgetsapp

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lists.*

class ListsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lists)

        // start up code
        // list params are 4 @:
//        list_farm.setOnClickListener{list: List, item:ClipData.Item, index:IndexedValue, id:IndexedValue ->
//            Toast.makeText(this, "List Selected at index: ", Toast.LENGTH_LONG).show()
//        }

//        list_farm.setOnClickListener(View.OnClickListener {
//            Toast.makeText(this, "List Selected at index: ", Toast.LENGTH_LONG).show()
//        })

        list_farm.setOnItemClickListener{list, item, index, id ->
            Toast.makeText(this, "List Selected at index: $index", Toast.LENGTH_SHORT).show()

        }

        list_dynamic.setOnItemClickListener{list, item, index, id ->
            Toast.makeText(this, "Dynamic List Selected at index: $index", Toast.LENGTH_SHORT).show()

        }
    }
}
