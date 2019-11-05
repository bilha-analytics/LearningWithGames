package com.example.todgames

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_play_game.*

class IntroGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        initIntroScreen()
    }


    fun initIntroScreen(){
        val name = findViewById<TextView>(R.id.txt_gameName)
        val intro = findViewById<TextView>(R.id.txt_gameIntro)
        val but = findViewById<Button>(R.id.but_startGame)
        name.text = "Bigger Number Game"
        intro.text = "Select the number that is bigger to win!!\nYou score for each correct answer. Final result is a percent of the total number of attempts."
        but.text = "Play"
    }

    fun startGameButtonClick(view: View){
        startActivity(Intent(this@IntroGameActivity, PlayGameActivity::class.java))
        finish()
    }
}
