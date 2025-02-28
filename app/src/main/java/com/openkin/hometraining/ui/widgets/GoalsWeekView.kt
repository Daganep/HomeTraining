package com.openkin.hometraining.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import androidx.constraintlayout.widget.ConstraintSet.START
import androidx.constraintlayout.widget.ConstraintSet.END
import androidx.constraintlayout.widget.ConstraintSet.TOP
import androidx.constraintlayout.widget.ConstraintSet.BOTTOM
import androidx.constraintlayout.widget.ConstraintSet.LEFT
import androidx.constraintlayout.widget.ConstraintSet.RIGHT
import androidx.constraintlayout.widget.ConstraintSet.WRAP_CONTENT
import com.openkin.hometraining.domain.model.Goals

class GoalsWeekView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: StyleRes? = null,
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val factor = context.resources.displayMetrics.density
    private var currentDayView = CurrentDayView(context)
    private val dayNumberViews = mutableListOf<TextView>()
    private val constraintSet = ConstraintSet()

    fun setGoalsData(goalsData: Goals) {
        constraintSet.clone(this)
        val dayNumber = goalsData.currentDayNumber
        initDayView(goalsData.currentWeak[dayNumber].toString())
        initDayNumberViews(goalsData.currentWeak)
        addViews(goalsData)
        constraintSet.applyTo(this)
    }

    private fun initDayNumberViews(days: List<Int>) {
        for (day in days) {
            val dayView = TextView(context)
            val id = View.generateViewId()
            dayView.id = id
            dayView.text = day.toString()
            dayView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17f)
            with(constraintSet) {
                constrainWidth(id, WRAP_CONTENT)
                constrainHeight(id, WRAP_CONTENT)
                connect(id, TOP, PARENT_ID, TOP)
                connect(id, BOTTOM, PARENT_ID, BOTTOM)
                setMargin(id, START, (24*factor).toInt())
            }
            dayNumberViews.add(dayView)
        }
    }

    private fun initDayView(dayNumber: String) {
        with(constraintSet) {
            val id = View.generateViewId()
            currentDayView.id = id
            //setMargin(id, START, (16*factor).toInt())
            constrainWidth(id, (32*factor).toInt())
            constrainHeight(id, (32*factor).toInt())
            connect(id, TOP, PARENT_ID, TOP)
            connect(id, BOTTOM, PARENT_ID, BOTTOM)
            currentDayView.setDayNumber(dayNumber)
        }
    }

    private fun addViews(goalsData: Goals) {
        val dayNumber = goalsData.currentDayNumber
        when(dayNumber) {
            0 -> currentDayAtFirst()
            6 -> currentDayAtLast()
            else -> currentDayBetween()
        }
    }

    private fun currentDayAtFirst() {
        val viewIds = mutableListOf<Int>()
        viewIds.add(currentDayView.id)
        constraintSet.connect(currentDayView.id, START, PARENT_ID, START)
        constraintSet.connect(currentDayView.id, END, dayNumberViews[1].id, START)
        addView(currentDayView)
        dayNumberViews.forEachIndexed { index, day ->
            when (index) {
                0 -> { }
                1 -> {
                    constraintSet.connect(day.id, START, currentDayView.id, END)
                    constraintSet.connect(day.id, END, dayNumberViews[index+1].id, START)
                }
                6 -> {
                    constraintSet.connect(day.id, START, dayNumberViews[index-1].id, END)
                    constraintSet.connect(day.id, END, PARENT_ID, END)
                }
                else -> {
                    constraintSet.connect(day.id, START, dayNumberViews[index-1].id, END)
                    constraintSet.connect(day.id, END, dayNumberViews[index+1].id, START)
                }
            }
            if (index != 0) {
                viewIds.add(day.id)
                addView(day)
            }
        }
        constraintSet.createHorizontalChain(PARENT_ID, LEFT, PARENT_ID, RIGHT, viewIds.toIntArray(), null, ConstraintSet.CHAIN_PACKED)
    }

    private fun currentDayAtLast() {

    }

    private fun currentDayBetween() {

    }
}
