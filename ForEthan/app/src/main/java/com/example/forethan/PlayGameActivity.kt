package com.example.forethan

import GameItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import kotlinx.android.synthetic.main.activity_game_intro.*
import kotlinx.android.synthetic.main.activity_play_game.*
import kotlinx.android.synthetic.main.que_and_two_buttons_widget.view.*
import kotlinx.android.synthetic.main.scores_item.*
import kotlinx.android.synthetic.main.scores_item.view.*
import kotlin.random.Random

class PlayGameActivity : AppCompatActivity() {

    private lateinit  var gameItem: GameItem
    private var maxInt = 100
    private val alphabet = CharArray(26){ (it+97).toChar() }
//    private lateinit var lexicon: ArrayList<String>
    private var lexicon = listOf<String>("Cow", "Cat", "Car", "Hat", "House", "Kite")

    private var count_down_timer = 30 //30 seconds
    private var count_clicks = 0
    private var count_correct = 0

    private lateinit var player_widget: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game)

        if(intent != null){
            gameItem = intent!!.getSerializableExtra("gameItem") as GameItem

            populateTracker()

            when( gameItem.id){
                GameItem.BIG_NUMBER_GAME -> populateQueAndTwoButtonPlayer()
                GameItem.BIG_LETTER_GAME -> populateQueAndTwoButtonPlayer()
                GameItem.WORD_GAME -> populateImageAndTwoButtonPlayer()

            }
        }
    }

    private fun populateTracker(){
        txt_timer_countDown.text = "$count_down_timer"
        widget_scores.txt_count_clicks.text = "0"
        widget_scores.txt_count_correct.text = "0"
        widget_scores.txt_percent_score.text = "0%"

        YoYo.with( Techniques.Flash)
            .duration(5000)
            .repeat( 10 )
            .playOn( txt_timer_countDown )
    }

    private fun populateQueAndTwoButtonPlayer(){
        player_widget = layoutInflater.inflate(R.layout.que_and_two_buttons_widget, null)
        player_widget.txt_question.text = "${gameItem.question_tag}"
        updateButtons()
        layout_player_widget.addView( player_widget )
    }

    fun populateImageAndTwoButtonPlayer(){
        player_widget = layoutInflater.inflate(R.layout.name_the_image_widget, null)
        player_widget.txt_question.text = "${gameItem.question_tag}"
        updateButtons()
        layout_player_widget.addView( player_widget )

    }

    private fun updateButtons() {

        val maxed = when( gameItem.id){
            GameItem.BIG_NUMBER_GAME -> maxInt
            GameItem.BIG_LETTER_GAME -> alphabet.size - 1
            GameItem.WORD_GAME -> lexicon.size - 1
            else -> 0
        }


        val num1 =  Random.nextInt( maxed )
        var num2 = num1
        while (num2 == num1 ){ num2 = Random.nextInt( maxed) }

        when( gameItem.id){
            GameItem.BIG_NUMBER_GAME -> setButtonText( "$num1", "$num2" )
            GameItem.BIG_LETTER_GAME -> setButtonText( "${alphabet[num1]}".toUpperCase(), "${alphabet[num2]}".toUpperCase() )
            GameItem.WORD_GAME -> setButtonText("${lexicon[num1]}", "${lexicon[num2]}")
        }
    }

    private fun setButtonText(num1: String, num2: String){
        player_widget.btn_left.text = num1
        player_widget.btn_right.text = num2
    }

    fun buttonClickedBigNumber(v: View){
        val left = player_widget.btn_left.text.toString()
        val right = player_widget.btn_right.text.toString()

        if( v == player_widget.btn_right || v == player_widget.btn_left){
            count_clicks++
            val b = v as Button
            val msg1 = "You selected ${ b.text }"
            var msg2 = ""

            msg2 = when( gameItem.id){
                GameItem.BIG_NUMBER_GAME -> if( left > right ) "$left is bigger than $right" else "$right is bigger than $left"
                GameItem.BIG_LETTER_GAME -> if( left > right ) "$left comes after $right" else "$right comes after $left"
                GameItem.WORD_GAME -> if( left > right ) "$left comes after $right" else "$right comes after $left"

                else -> ""
            }

            var kudos = ""
            if( (v == player_widget.btn_left && left > right) or
                (v == player_widget.btn_right && right > left) ){
                count_correct++
                kudos = "Awesome!! Good job!!"
            }else{
                kudos = "Ooh no, that's incorrect. Let's try that again!!"
            }

            player_widget.txt_question.text = "$msg2\n$msg1\n\n$kudos"
            txt_count_clicks.text = "$count_clicks"
            txt_count_correct.text = "$count_correct"
            txt_percent_score.text = " ${count_correct/count_clicks*100}%"
            updateButtons()
        }


    }

}
