package com.example.widgetsapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_word.*
import java.io.PrintStream

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class EditWordActivity : AppCompatActivity() {

    private var item_index = -9
    private var incoming_text = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_word)

        val msg = this.intent.getStringExtra( "dline")
        txt_editLine.setText( "$msg" )
        item_index = this.intent.getIntExtra( "index", 0)
    }


    fun cancelButton(v: View){
        feedbackShow("Changes discarded")
        finish()
    }

    fun saveButton(v: View){
        val changeLine = findViewById<EditText>(R.id.txt_editLine).text.toString()
        feedbackShow("Changes added!")
        sendBackToDataStorageForAction( changeLine )
    }

    private fun feedbackShow(msg:String){
        Toast.makeText(this, "$msg", Toast.LENGTH_SHORT ).show()
    }

    private fun sendBackToDataStorageForAction(newLine:String){
        val intent = Intent()
        intent.putExtra("newLine", "$newLine" )
        intent.putExtra("index", item_index )
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
