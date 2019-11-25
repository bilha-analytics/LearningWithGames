package com.example.mystreams

import java.io.Serializable

class AppMode{

    data class MenuItem(val name:String, val description:String, val icon:Int, val urlCurrent:String) : Serializable{
        override fun toString(): String {
            return name
        }
    }


    companion object{
        val menuList = listOf<MenuItem>(
            MenuItem( "XKCD", "Daily xkcd comic stream", R.drawable.xkcd, "http://xkcd.com/info.0.json" ),
            MenuItem("Makeup", "Major/Common makeup products stream", R.drawable.makeup, "https://makeup-api.herokuapp.com/api/v1/products.json" ),
            MenuItem("Quotes", "Funny, inspirational, stupid, famous, mean, random quotes", R.drawable.quotes, "https://quote-garden.herokuapp.com/quotes/random")
        )


        lateinit var menuItem: MenuItem
        lateinit var prevItem: MenuItem
    }

}