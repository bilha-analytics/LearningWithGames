package com.example.forethan

import GameItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_game_intro.*
import kotlinx.android.synthetic.main.activity_play_game.*
import kotlinx.android.synthetic.main.scores_item.*
import kotlinx.android.synthetic.main.scores_item.view.*
import kotlin.random.Random

class PlayGameActivity : AppCompatActivity() {

    private lateinit  var gameItem: GameItem
    private var maxInt = 100

    private var count_down_timer = 30 //30 seconds
    private var count_clicks = 0
    private var count_correct = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game)

        if(intent != null){
            gameItem = intent!!.getSerializableExtra("gameItem") as GameItem
            populate()
        }
    }

    private fun populate(){
        txt_timer_countDown.text = "$count_down_timer"
        txt_question.text = "${gameItem.question_tag}"
        widget_scores.txt_count_clicks.text = "0"
        widget_scores.txt_count_correct.text = "0"
        widget_scores.txt_percent_score.text = "0%"

        updateButtons()
    }

    private fun updateButtons(){
        val num1 = Random.nextInt(maxInt)
        var num2 = num1
        while (num2 == num1){
            num2 = Random.nextInt(maxInt)
        }

        btn_left.text = "$num1"
        btn_right.text = "$num2"
    }

    fun buttonClicked(v: View){
        val left = btn_left.text.toString().toInt()
        val right = btn_right.text.toString().toInt()

        if( v == btn_right || v == btn_left){
            count_clicks++
            val b = v as Button
            val msg1 = "You selected ${ b.text }"
            val msg2 = if( left > right ) "$left is bigger than $right" else "$right is bigger than $left"
            var kudos = ""
            if( (v == btn_left && left > right) or (v == btn_right && right > left) ){
                count_correct++
                kudos = "Awesome!! Good job!!"
            }else{
                kudos = "Ooh no, that's incorrect. Let's try that again!!"
            }

            txt_question.text = "$msg2\n$msg1\n\n$kudos"
            txt_count_clicks.text = "$count_clicks"
            txt_count_correct.text = "$count_correct"
            txt_percent_score.text = " ${count_correct/count_clicks*100}%"
            updateButtons()
        }


    }

}
