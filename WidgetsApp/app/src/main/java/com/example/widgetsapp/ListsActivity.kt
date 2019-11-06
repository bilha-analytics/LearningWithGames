package com.example.widgetsapp

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lists.*

class ListsActivity : AppCompatActivity() {


    private val data = ArrayList<String>()
    private lateinit var listAdapter : ArrayAdapter<String>

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



        // populate data model
        data.add("Car")
        data.add("Lorry")
        data.add("Tractor")
        data.add("Bus")
        data.add("Train")
        data.add("Ship")
        data.add("Boat")
        data.add("Yatch")
        data.add("Motorbike")
        data.add("Bicycle")

        println( ">>>> ListArray of size ${data.size}: $data\n First item = ${data[0] }" )

        // get random four
//        data.shuffle()
//        println(">>>>> Random 4: ${ data.subList(0,4)}")


        // array adapter --> to an array or list
        // cursor adapter --> to a database query
        listAdapter = ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, data)
        list_dynamic.adapter = listAdapter





        list_farm.setOnItemClickListener{_, _, index, _ ->
            Toast.makeText(this, "List Selected at index: $index", Toast.LENGTH_SHORT).show()

        }

        list_dynamic.setOnItemClickListener{list, item, index, id ->
            Toast.makeText(this, "Dynamic List Selected at index: $index", Toast.LENGTH_SHORT).show()
            data.removeAt( index )
            data.set( index+1, "${data.get(index+1)  } | up" )
            listAdapter.notifyDataSetChanged()
        }

    }
}
