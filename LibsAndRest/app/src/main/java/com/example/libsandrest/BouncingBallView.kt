package com.example.libsandrest

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class BouncingBallView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {

    val painter = Paint()
    var imgX = 0f
    var imgY = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if( canvas == null) return
        paintCircle(canvas)

        // this is non-blocking on the UI
        Thread(){
            animateImg()
        }.start()
    }

    fun paintCircle(canvas: Canvas){
        painter.setARGB(40, 204, 89, 204)
        canvas.drawRect(RectF(0f, 0f, 100f, 100f), painter)
        painter.setARGB(255, 0,0,0)
        painter.typeface = Typeface.create( Typeface.MONOSPACE, Typeface.BOLD_ITALIC)
        painter.textSize = 70f
        canvas.drawText("ABC", 5f, 105f, painter)

        var img = BitmapFactory.decodeResource(resources, R.drawable.animals_game)
        img = Bitmap.createScaledBitmap( img, 100, 100, false)
        canvas.drawBitmap( img, imgX, imgY, null)
    }

    fun animateImg(){
        imgX = Random.nextInt( 100 ).toFloat()
        imgY = Random.nextInt( 100 ).toFloat()
        // this is blocking
        Thread.sleep( 2000 )
        postInvalidate()
    }

}