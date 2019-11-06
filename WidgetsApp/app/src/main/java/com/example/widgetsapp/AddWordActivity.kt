package com.example.widgetsapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.io.PrintStream

class AddWordActivity : AppCompatActivity() {
    private val DATA_FILE_NAME = "output.data2.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)

        //read data passed from previous Activity
//        val data = this.intent.getStringExtra("named")

        //startup code


    }

    fun cancelButtonClick(v:View){
        feedbackShow("Sentense not added!")
        goBack()
    }


    fun saveButtonClick(v: View){
        val newLine = findViewById<EditText>(R.id.txt_addText).text.toString()
//        val newLine = txt_addText.text.toString()

        // add to file from here
//        persistNewDataToFile(newLine)

        // pass new data back to initiating activity for action
        sendBackToDataStorageForAction(newLine)

        feedbackShow("Sentence has been added!")

        //no need if passing back; finish will do its thing
//        goBack()
    }

    private fun goBack(){
        //Opt 1: this forces onCreate  - so resets everything
        startActivity(Intent(this, DataStorageActivity::class.java))
        finish()

        //Opt 2:
    }

    private fun feedbackShow(msg:String){
        Toast.makeText(this, "$msg", Toast.LENGTH_SHORT ).show()
    }


    private fun persistNewDataToFile(line:String){
        val writer = PrintStream( openFileOutput(DATA_FILE_NAME, Context.MODE_APPEND) )
        writer.println( line )
        writer.close()
    }

    private fun sendBackToDataStorageForAction(newLine:String){
        val intent = Intent()
        intent.putExtra("newLine", "$newLine" )
        setResult(Activity.RESULT_OK, intent)
        finish()

    }
}
