package com.example.todgames

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_games_list.*

class GamesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }


    fun onClickNumbersGame(view: View){
//        setNextActivity(this@GamesListActivity, NumbersGameActivity::class.java)
        startActivity(Intent(this@GamesListActivity, IntroGameActivity::class.java) )
        finish()
    }
    fun onClickLettersGame(view: View){
//        setNextActivity(this@GamesListActivity, NumbersGameActivity::class.java)
        startActivity(Intent(this@GamesListActivity, NumbersGameActivity::class.java))
        finish()
    }
    fun onClickAnimalsGame(view: View){
//        setNextActivity(this@GamesListActivity, NumbersGameActivity::class.java)
        startActivity(Intent(this@GamesListActivity, NumbersGameActivity::class.java))
        finish()
    }

//    fun setNextActivity(currentContext: Context, nextClass: Activity){
//        startActivity(Intent(currentContext, nextClass) )
//        finish()
//    }
}
