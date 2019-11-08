package com.example.forethan

import GameItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
            loadGame( v!!.id.toString() )
            Log.d("imgBut", "selected {${v.id.toString()}} ")
        }

        home_list_grid.addView( widget )
    }


    private fun loadGame(id: String ){
        when(id){
        }
    }

    private fun getGameWidgetID(gameName:String):String{
        return gameName.toLowerCase().replace(" ", "_")
    }
    private  fun loadGamesList(){
        gamesList.add(
            GameItem(
                "Big Number game",
                "Show us which number is larger.\nSelect the number that is bigger than the other to earn points",
                R.drawable.number_game
            ) )


        gamesList.add(
            GameItem(
                "Capital Letters game",
                "Show us which small letter goes with this capital letter.\nSelect the small letter that goes with this capital letter to earn points",
                R.drawable.alphabet2
            ) )


        gamesList.add(
            GameItem(
                "Word game",
                "How do you  write the name of the object in the picture?\nSelect the word that matches the picture to earn points",
                R.drawable.word_game
            ) )


        gamesList.add(
            GameItem(
                "Animals game",
                "Which animal makes this sound.\nSelect the animal that makes that sound to earn points",
                R.drawable.animals_game
            ) )


        gamesList.add(
            GameItem(
                "Counting game",
                "Can you help me identify which number comes next?\nSelect the number that comes next to earn points",
                R.drawable.number_game2
            ) )

    }
}
