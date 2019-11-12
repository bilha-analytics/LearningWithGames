package com.example.libsandrest

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import stanford.androidlib.graphics.GCanvas
import stanford.androidlib.graphics.GColor
import stanford.androidlib.graphics.GOval
import kotlin.random.Random

class MartysView(context: Context?, attrs: AttributeSet?) :
    GCanvas(context, attrs){


    var imgX = 10f
    var imgY = 20f
    val ball = GOval(10f, 10f, 102f, 202f)

    val maxfall = 20f
    val drops = ArrayList<GOval>()

    override fun init() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        val pt = Paint()
//            pt.setARGB(255, 255, 0, 255)
        imgX = Random.nextInt( 10, 15 ).toFloat()
        imgY = Random.nextInt( 10, 20 ).toFloat()
        ball.fillColor = GColor.PURPLE
        add( ball )


        makeRain()

        animate(30){ move() }

    }

    fun move(){

        if( ball.rightX >= width || ball.x <= 0 ){
            imgX = -imgX
        }
        if( ball.bottomY >= height || ball.y <= 0 ){
            imgY = -imgY
        }

        ball.moveBy(imgX, imgY)

        for( drop in drops ){
            drop.moveBy(0f, Random.nextInt(maxfall.toInt()).toFloat() )
            if(drop.y >= width){ drop.y = 0f }
        }
    }


    fun makeRain(){
        val raindrop_size = 2f
        for( i in 0..100){
            val x = Random.nextInt( (width - raindrop_size).toInt() ).toFloat()
            val y = Random.nextInt(width.toInt()).toFloat() //Random.nextInt(maxfall.toInt()).toFloat()
            val drop = GOval( x, y, raindrop_size, raindrop_size*3              )
            drop.fillColor = GColor.LIGHT_GRAY
            drop.color = GColor.GRAY
            add(  drop )
            drops.add( drop )
        }

    }

    fun dropRain(){

    }

}