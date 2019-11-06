package com.example.widgetsapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_data_storage.*
import java.io.BufferedReader
import java.io.OutputStream
import java.io.PrintStream
import java.util.*
import kotlin.collections.ArrayList

class DataStorageActivity : AppCompatActivity() {

    private val DATA_FILE_NAME = "output.data.txt"
    private val ADD_WORD_CODE = 123
    private val EDIT_WORD_CODE = 999

    private val data_model = ArrayList<String>()
    private lateinit var lsAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_storage)



        // internal storage is private to the app only. Found at /res/raw. Part of jar
        // external storage is sharable with others
        readSampleFile()

        setupListModel()

        //instead of println use Log
        Log.d("Logger.init", ">>>>*******FINISHED onCreate")
    }

    private fun setupListModel(){
        lsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data_model )
        ls_file.adapter = lsAdapter

        ls_file.setOnItemClickListener{ _, item,  index, _ ->
//            data_model.removeAt( index )
//            lsAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Selected list item at $index", Toast.LENGTH_SHORT).show()

            val mintent = Intent(this, EditWordActivity::class.java)
            mintent.putExtra("dline", "$item")
            mintent.putExtra("index", index)
            startActivityForResult(mintent, EDIT_WORD_CODE)
        }

    }
    private fun readSampleFile(){

        try{
            val reader = Scanner(openFileInput(DATA_FILE_NAME) )
            while (reader.hasNextLine()) {
                data_model.add(reader.nextLine())
            }
            reader.close()
        }catch (e:Exception ) {

            val reader = Scanner(resources.openRawResource(R.raw.sample_data))
            while (reader.hasNextLine()) {
                data_model.add(reader.nextLine())
            }
            reader.close()
        }
    }

    fun addNewLineButtonClick(view: View){
        //Opt 1: non-blocking start
//        startActivity( Intent(this, AddWordActivity::class.java))
        // this clears it from stack
//        finish()

        //opt 2: blocking for result
        startActivityForResult( Intent(this, AddWordActivity::class.java), ADD_WORD_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
        Log.d(">>>>>>>>*****:::: ", "$requestCode | $resultCode | $data")
        if (requestCode == ADD_WORD_CODE && resultCode == Activity.RESULT_OK) {
            val newLine = data!!.getStringExtra("newLine")
            Log.d(">>>>>*******pass.intent", "$newLine")
            data_model.add(newLine)
            lsAdapter.notifyDataSetChanged()
            persistDataToFile()

            feedbackShow("Sentence saved to file!")
        } else if (requestCode == EDIT_WORD_CODE && resultCode == Activity.RESULT_OK) {
            val newLine = data!!.getStringExtra("newLine")
            val index = data.getIntExtra("index", -99)
            data_model.set(index, newLine)
            lsAdapter.notifyDataSetChanged()
            persistDataToFile()

            feedbackShow("Sentence saved to file!")
        }
    }

    fun persistDataToFile(){
        val writer = PrintStream( openFileOutput(DATA_FILE_NAME, Context.MODE_PRIVATE) )
        for (line in data_model){
            writer.println( line )
        }
        writer.close()
    }


    private fun feedbackShow(msg:String){
        Toast.makeText(this, "$msg", Toast.LENGTH_SHORT ).show()
    }


}
