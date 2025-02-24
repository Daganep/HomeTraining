package com.openkin.hometraining.ui.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import com.openkin.hometraining.R

class CurrentDayView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private var canvas: Canvas? = null
    private val paintStroke = Paint()
    private val paintFill = Paint()
    private val paintDayNumber = Paint()
    private var dayNumber = ""
    private var centerHorizontal = 0f
    private var centerVertical = 0f
    private var radius = 0f
    private val strokeWidth = 4f

    init {
        setupFillPaint()
        setupStrokePaint()
        setupTextPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        this.canvas = canvas
        centerHorizontal = width/2f
        centerVertical = height/2f
        radius = centerHorizontal
        drawStroke()
        drawFill()
        drawDayNumber()
    }

    fun setDayNumber(number: Char) {
        dayNumber = number.toString()
    }

    private fun drawStroke() {
        canvas?.drawCircle(centerHorizontal, centerVertical,radius, paintFill)
    }

    private fun drawFill() {
        canvas?.drawCircle(centerHorizontal, centerVertical,(radius - (strokeWidth/2)), paintStroke)
    }

    private fun drawDayNumber() {
        val numberRect = Rect()
        paintDayNumber.getTextBounds(dayNumber, 0, dayNumber.length, numberRect)
        val textStartX = centerHorizontal - numberRect.exactCenterX()
        val textStartY = centerVertical - numberRect.exactCenterY()
        canvas?.drawText(dayNumber, textStartX, textStartY, paintDayNumber)
    }

    private fun setupStrokePaint() {
        paintStroke.style = Paint.Style.STROKE
        paintStroke.strokeWidth = strokeWidth
        paintStroke.color = context.resources.getColor(R.color.blue_light, null)
    }

    private fun setupFillPaint() {
        paintFill.style = Paint.Style.FILL
        paintFill.color = context.resources.getColor(R.color.gray_medium, null)
    }

    private fun setupTextPaint() {
        paintDayNumber.style = Paint.Style.FILL
        paintDayNumber.color = context.resources.getColor(R.color.blue_dark, null)
        paintDayNumber.textSize = NUMBER_TEXT_SIZE
        paintDayNumber.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }

    companion object {
        private const val NUMBER_TEXT_SIZE = 36f
    }
}
