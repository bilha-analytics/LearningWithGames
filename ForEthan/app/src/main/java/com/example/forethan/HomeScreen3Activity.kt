package com.example.forethan

import GameItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_home_screen3.*
import kotlinx.android.synthetic.main.game_item_widget.view.*

class HomeScreen3Activity : AppCompatActivity() {


    var gamesList = ArrayList<GameItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen3)

        loadGamesList()

//        var i = 1
//        val btn = "btn_game_"
//        for( game in gamesList){
//            val v = findViewById(R.id.id)
//        }

        btn_game_1.game_name_txt.text = gamesList[0].name
        btn_game_1.game_icon_btn.setImageResource( gamesList[0].iconID )
        btn_game_1.setOnClickListener{
            onGameButtonClick(0)
        }

        btn_game_2.game_name_txt.text = gamesList[1].name
        btn_game_2.game_icon_btn.setImageResource( gamesList[1].iconID )
        btn_game_1.setOnClickListener{
            onGameButtonClick(1)
        }

        btn_game_3.game_name_txt.text = gamesList[2].name
        btn_game_3.game_icon_btn.setImageResource( gamesList[2].iconID )
        btn_game_1.setOnClickListener{
            onGameButtonClick(2)
        }

        btn_game_4.game_name_txt.text = gamesList[3].name
        btn_game_4.game_icon_btn.setImageResource( gamesList[3].iconID )
        btn_game_1.setOnClickListener{
            onGameButtonClick(3)
        }

        btn_game_5.game_name_txt.text = gamesList[4].name
        btn_game_5.game_icon_btn.setImageResource( gamesList[4].iconID )
        btn_game_1.setOnClickListener{
            onGameButtonClick(4)
        }
    }

    private fun onGameButtonClick(index: Int){
        val game = gamesList[index]
        Log.d("GameSelected", "$index for {${game.name}" )
    }
    private  fun loadGamesList() {
        gamesList.add(
            GameItem(
                "Big Number game",
                "Show us which number is larger.\nSelect the number that is bigger than the other to earn points",
                R.drawable.number_game,
                "Something"
            )
        )


        gamesList.add(
            GameItem(
                "Capital Letters game",
                "Show us which small letter goes with this capital letter.\nSelect the small letter that goes with this capital letter to earn points",
                R.drawable.alphabet2,
                "Something"
            )
        )


        gamesList.add(
            GameItem(
                "Word game",
                "How do you  write the name of the object in the picture?\nSelect the word that matches the picture to earn points",
                R.drawable.word_game,
                "Something"
            )
        )


        gamesList.add(
            GameItem(
                "Animals game",
                "Which animal makes this sound.\nSelect the animal that makes that sound to earn points",
                R.drawable.animals_game,
                "Something"
            )
        )


        gamesList.add(
            GameItem(
                "Counting game",
                "Can you help me identify which number comes next?\nSelect the number that comes next to earn points",
                R.drawable.number_game2,
                "Something"
            )
        )
    }

    }
