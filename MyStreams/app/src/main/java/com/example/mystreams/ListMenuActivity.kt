package com.example.mystreams

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_menu.*

class ListMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_menu)

        ls_streamsMenu.adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, AppMode.menuList)

        ls_streamsMenu.setOnItemClickListener { _, item, index, _  ->
            Log.d("mystream", "selected item = $item at index = $index")
            processListItemSelected(index)
        }
    }


    private fun processListItemSelected(index:Int){
        val menuItem = AppMode.menuList[index]
        Toast.makeText(this@ListMenuActivity, "selected item = ${menuItem.urlCurrent} at index = $index", Toast.LENGTH_SHORT ).show()

        val gonext = Intent(this, PlayerViewActivity::class.java)
        gonext.putExtra( "menuItem", menuItem)
        startActivity( gonext )
    }
}
