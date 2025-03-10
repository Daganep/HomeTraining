package com.openkin.hometraining.ui.widgets

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.openkin.hometraining.R
import com.openkin.hometraining.databinding.LayoutNavigationButtonBinding

class NavigationButtonView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : LinearLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    private var binding: LayoutNavigationButtonBinding
    private var isActive: Boolean = false

    init {
        inflate(context, R.layout.layout_navigation_button, this)
        binding = LayoutNavigationButtonBinding.bind(this)
        setAttrs(attributeSet, defStyleAttr, defStyleRes)
        updateButtonState()
    }

    override fun performClick(): Boolean {
        if (!isActive) {
            isActive = true
            updateButtonState()
        }
        return super.performClick()
    }

    fun setInactive() {
        if (isActive) {
            isActive = false
            updateButtonState()
        }
    }

    private fun setAttrs(attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attributeSet == null) return
        val typedArray: TypedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.NavigationButtonView,
            defStyleAttr,
            defStyleRes,
        )

        with(binding) {
            val text = typedArray.getString(R.styleable.NavigationButtonView_button_text) ?: ""
            val icon = typedArray.getDrawable(R.styleable.NavigationButtonView_button_icon)
            isActive = typedArray.getBoolean(R.styleable.NavigationButtonView_active, false)
            buttonText.text = text
            if (icon != null) buttonIcon.setImageDrawable(icon)
        }

        typedArray.recycle()
    }

    private fun updateButtonState() {
        val color = if (isActive) ContextCompat.getColor(context, R.color.blue_dark)
        else ContextCompat.getColor(context, R.color.inactive_navigation_button)
        binding.buttonText.setTextColor(color)
        binding.buttonIcon.setColorFilter(color)
    }
}
