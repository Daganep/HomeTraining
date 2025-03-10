package com.openkin.hometraining.ui.widgets

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import coil.load
import com.openkin.hometraining.R
import com.openkin.hometraining.databinding.ItemSevenFourProgramBinding
import com.openkin.hometraining.databinding.ItemTrainingsGroupBinding
import com.openkin.hometraining.databinding.ItemTrainingsProgramsBinding
import com.openkin.hometraining.domain.model.ProgramSevenFour

class SevenFourProgramView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: StyleRes? = null,
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private var binding: ItemSevenFourProgramBinding

    init {
        inflate(context, R.layout.item_seven_four_program, this)
        binding = ItemSevenFourProgramBinding.bind(this)
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
