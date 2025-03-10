package com.openkin.hometraining.ui.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import coil.load
import com.openkin.hometraining.R
import com.openkin.hometraining.databinding.LayoutSevenFourProgramBinding
import com.openkin.hometraining.domain.model.ProgramSevenFour

class SevenFourProgramView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : ConstraintLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    private var binding: LayoutSevenFourProgramBinding

    init {
        inflate(context, R.layout.layout_seven_four_program, this)
        binding = LayoutSevenFourProgramBinding.bind(this)
    }

    fun setData(data: ProgramSevenFour) {
        with(binding) {
            backgroundImage.load(data.image)
            if (data.donePercent == 0) {
                newProgramName.text = data.programName
                newProgramDescription.text = data.description
                setInProgress(false)
            } else {
                progressProgramName.text = data.programName
                progress.progress = data.donePercent
                progressProgramLabel.text = "${data.donePercent} закончить"
                progressProgramDay.text = "${data.currentDay} день"
                setInProgress(true)
            }
        }
    }

    private fun setInProgress(inProgress: Boolean) {
        with(binding) {
            newProgramName.isVisible = !inProgress
            newProgramDescription.isVisible = !inProgress
            progressProgramName.isVisible = inProgress
            progress.isVisible = inProgress
            progressProgramLabel.isVisible = inProgress
            progressProgramDay.isVisible = inProgress
        }
    }
}
