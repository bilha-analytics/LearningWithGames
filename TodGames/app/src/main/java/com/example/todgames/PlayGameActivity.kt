package com.example.todgames

import android.icu.text.NumberFormat
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_play_game2.*
import kotlin.random.Random

class PlayGameActivity : AppCompatActivity() {

    var timeCounter = 30
    var points = 0
    var countClicks = 0

    var gameStarted = false
    var maxNumber = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game2)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        initGameScreen(-99)
        gameStarted = true
    }

    fun buttonClick(view: View){
        val selected_id = view.id
        if( selected_id == R.id.but_left || selected_id == R.id.but_right){
            countClicks++
            initGameScreen(selected_id)
        }


    }


    fun initGameScreen(selectedID: Int){

        if(!gameStarted){
            timeCounter = 30
            points = 0
            countClicks = 0
            val que = findViewById<TextView>(R.id.txt_question)
            val timer = findViewById<TextView>(R.id.txt_timer)
            que.text = "Which is bigger?"
            timer.text = "$timeCounter sec"
        }

        val score = findViewById<TextView>(R.id.txt_score)
        val left = findViewById<Button>(R.id.but_left)
        val right = findViewById<Button>(R.id.but_right)
        val feedback = findViewById<TextView>(R.id.txt_feedback)

        var fbMsg: String = ""

        if(selectedID != -99){
            val lval = left.text.toString().toInt()
            val rval = right.text.toString().toInt()

            val youSelected = findViewById<Button>(selectedID).text.toString()
            val msg2 = if(lval > rval) "$lval is greater than $rval" else "$rval is greater than $lval"
            val msgIntro = "$msg2.\nYou selected $youSelected"
            if( (selectedID == R.id.but_left && lval > rval) ||
                (selectedID == R.id.but_right && rval > lval ) ){
                points++
                fbMsg = "$msgIntro\n\nAwesome!! Good job!!"

            }else{
                fbMsg = "$msgIntro\n\nOoh no! That's incorrect.\nLet's try again!"
            }
        }

        var pctScore = if(selectedID == -99) 0 else NumberFormat.getNumberInstance().format((points/countClicks)*100)

        feedback.text = if(selectedID==-99) "Are you ready? let's get started champ!!" else fbMsg

        score.text = "$pctScore% \n$points correct"

        val leftValue = getNextRandomNumber()
        var rightValue = leftValue
        while( rightValue == leftValue){
            rightValue = getNextRandomNumber()
        }

        left.text = "$leftValue"
        right.text = "$rightValue"
    }

    fun getNextRandomNumber(): Int {
        return Random.nextInt( maxNumber )
    }
}
