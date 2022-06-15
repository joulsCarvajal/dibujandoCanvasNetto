package com.example.drawingoncanvas

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.res.ResourcesCompat
import kotlin.math.abs

private const val stroke_width = 6f
class MiCanvas(context: Context): View(context) {

    // Color del back
    private val backColor = ResourcesCompat.getColor(resources, R.color.white,null)
    private lateinit var canvas1:Canvas
    private lateinit var bitmap1:Bitmap

    // Color de la linea o lapiz
    private val penColor = ResourcesCompat.getColor(resources, R.color.black,null)
    private val touchTolerance = ViewConfiguration.get(context).scaledEdgeSlop

    private val paint = Paint().apply{
        color = penColor
        isAntiAlias = true //Suavizado?
        isDither = true //difuminado?
        style = Paint.Style.STROKE
        strokeWidth = stroke_width //anchura del trazo
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    private var path = Path()
    private var motionX = 100f
    private var motionY = 100f
    private var currentX = 101f
    private var currentY = 101f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if(::bitmap1.isInitialized)bitmap1.recycle() //Esto ayudará a que no hayan perdidas de
        // memoria por el cambio de tamaño y recicla esas posibles pérdidas de memoria.

        bitmap1 = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
        canvas1 = Canvas(bitmap1)
        canvas1.drawColor(backColor)
        canvas1.drawLine(149f,149f,289f,299f,paint)
    }

    override fun onDraw(canvas: Canvas?) {
        var ovalo = arrayOf(169f, 179f, 180f)
        super.onDraw(canvas)
        canvas?.drawBitmap(bitmap1, 100f, 0f, null)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)

        motionX = event!!.x
        motionY = event!!.y

        when(event.action){
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUP()
        }
        return true
    }


    private fun touchUP() {
        path.reset()
    }

    private fun touchMove() {
        val dx = abs(motionX-currentX)
        val dy = abs(motionY-currentY)

        if(dx >= touchTolerance || dy >= touchTolerance)
        {
            path.quadTo(currentX,currentY,(motionX+currentX)/2, (motionY+currentY)/2)
            currentX = motionX
            currentY = motionY
            canvas1.drawPath(path,paint)
        }
    }

    private fun touchStart() {
        path.reset()
        path.moveTo(motionX, motionY)
        currentX = motionX
        currentY = motionY
    }
}