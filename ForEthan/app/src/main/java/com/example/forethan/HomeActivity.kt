package com.example.forethan

import GameItem
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.game_item_widget.view.*

class HomeActivity : AppCompatActivity() {

    var gamesList = ArrayList<GameItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        loadGamesList()

        for( game in gamesList){
            addGame(game)
        }
    }


    private fun addGame(gameItem: GameItem){
        val widget = layoutInflater.inflate(R.layout.game_item_widget, null)
        widget.game_name_txt.text = gameItem.name
        widget.game_icon_btn.setImageResource( gameItem.iconID )
        widget.game_icon_btn.setTag( getGameWidgetID(gameItem.name ) )

        widget.game_icon_btn.setOnClickListener{v: View? ->
            YoYo.with(Techniques.Wobble).duration(2000).playOn( widget )
            loadGame( gameItem )
            Log.d("imgBut", "selected ${v!!.id} for ${gameItem.name}")
        }

        home_list_grid.addView( widget )
    }


    private fun loadGame(gameItem: GameItem ){
        val intentd = Intent( this, GameIntroActivity::class.java)
        intentd.putExtra("gameItem", gameItem)
        startActivity(intentd)
    }

    private fun getGameWidgetID(gameName:String):String{
        return gameName.toLowerCase().replace(" ", "_")
    }
    private  fun loadGamesList(){
        gamesList.add(
            GameItem( GameItem.BIG_NUMBER_GAME,
                "Big Number game",
                "Show us which number is larger.\nSelect the number that is bigger than the other to earn points",
                R.drawable.number_game,
                "Which number is bigger?"
            ) )


        gamesList.add(
            GameItem(GameItem.BIG_LETTER_GAME,
                "Capital Letters game",
                "Show us which letter comes next\nSelect the letter that comes after to earn points",
                R.drawable.alphabet2,
                "Which letter comes after the other?"
            ) )


        gamesList.add(
            GameItem(GameItem.WORD_GAME,
                "Word game",
                "How do you  write the name of the object in the picture?\nSelect the word that matches the picture to earn points",
                R.drawable.word_game,
                "Which is the correct word?"
            ) )


        gamesList.add(
            GameItem(GameItem.COUNTING_GAME,
                "Animals game",
                "Which animal makes this sound.\nSelect the animal that makes that sound to earn points",
                R.drawable.animals_game,
                "Which animal makes that sound?"
            ) )


        gamesList.add(
            GameItem(GameItem.COUNTING_GAME,
                "Counting game",
                "Can you help me identify which number comes next?\nSelect the number that comes next to earn points",
                R.drawable.number_game2,
                "Which number comes after?"
            ) )

    }
}
