package com.example.forethan

import GameItem
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import kotlinx.android.synthetic.main.activity_game_intro.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.game_item_widget.view.*
import kotlin.random.Random

class GameIntroActivity : AppCompatActivity() {

    private lateinit var gameItem: GameItem

    private val repeat_effect = 10
    private val effects = listOf<Techniques>( Techniques.Tada, Techniques.Bounce, Techniques.Wave, Techniques.Wobble)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_intro)

        if(intent != null){
            gameItem = intent!!.getSerializableExtra("gameItem") as GameItem
            populate()
            btn_playGame.setOnClickListener{
                buttonPlayGameClicked(it)
            }
        }
    }

    private fun populate(){
        icon_game.game_icon_btn.setImageResource( gameItem.iconID )
        icon_game.game_name_txt.text = gameItem.name
        txt_gameIntro.text = gameItem.description

        YoYo.with( effects[Random.nextInt( effects.size - 1) ])
            .duration(3000)
            .repeat(repeat_effect)
            .playOn( icon_game )

    }
    fun buttonPlayGameClicked(v: View){
        val intentd = Intent(this, PlayGameActivity::class.java)
        intentd.putExtra("gameItem", gameItem)
        startActivity(intentd)
        finish()
    }

    fun buttonBackClicked(v:View){
        finish()
    }
}
