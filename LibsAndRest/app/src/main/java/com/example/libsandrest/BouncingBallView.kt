package com.example.libsandrest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View

class BouncingBallView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {

    val painter = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if( canvas == null) return
        paintCircle(canvas)
    }

    fun paintCircle(canvas: Canvas){
        painter.setARGB(40, 204, 89, 204)
        canvas.drawRect(RectF(0f, 0f, 100f, 100f), painter)
        painter.setARGB(255, 0,0,0)
        painter.typeface = Typeface.create( Typeface.MONOSPACE, Typeface.BOLD_ITALIC)
        painter.textSize = 70f
        canvas.drawText("ABC", 5f, 105f, painter)
    }

}