package com.openkin.hometraining.ui.home.list.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.openkin.hometraining.R
import com.openkin.hometraining.createBinding
import com.openkin.hometraining.databinding.ItemTrainingsProgrammsBinding
import com.openkin.hometraining.ui.home.list.TrainingsAdapterType
import com.openkin.hometraining.ui.home.list.TrainingsDelegate
import com.openkin.hometraining.ui.home.list.TrainingsViewHolder

internal class ProgramsDelegate : TrainingsDelegate<ItemTrainingsProgrammsBinding,
    ProgramsDelegate.ProgramsType> {

    override fun isRelative(item: TrainingsAdapterType) = item is ProgramsType

    override fun getLayoutId() = R.layout.item_trainings_programms

    override fun getViewHolder(parent: ViewGroup) =
        ProgramsViewHolder(createBinding(parent, ItemTrainingsProgrammsBinding::inflate))

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<ProgramsType>() {
        override fun areItemsTheSame(oldItem: ProgramsType, newItem: ProgramsType) = true

        override fun areContentsTheSame(oldItem: ProgramsType, newItem: ProgramsType) = true
    }

    inner class ProgramsViewHolder(
        binding: ItemTrainingsProgrammsBinding
    ) : TrainingsViewHolder<ItemTrainingsProgrammsBinding, ProgramsType>(binding) {

        override fun onBind(item: ProgramsType) {
            //реализовать заполнение программ 7x4
        }
    }

    data class ProgramsType(
        val fullBodyProgramName: String,
        val downBodyProgramName: String,
        val onBeginClicked: (() -> Unit)? = null
    ) : TrainingsAdapterType
}
