package com.openkin.hometraining.ui.home.list.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.openkin.hometraining.R
import com.openkin.hometraining.createBinding
import com.openkin.hometraining.databinding.ItemTrainingsGroupBinding
import com.openkin.hometraining.ui.home.list.TrainingsAdapterType
import com.openkin.hometraining.ui.home.list.TrainingsDelegate
import com.openkin.hometraining.ui.home.list.TrainingsViewHolder

internal class GroupDelegate : TrainingsDelegate<ItemTrainingsGroupBinding,
    GroupDelegate.GroupType> {

    override fun isRelative(item: TrainingsAdapterType) = item is GroupType

    override fun getLayoutId() = R.layout.item_trainings_group

    override fun getViewHolder(
        parent: ViewGroup
    ): TrainingsViewHolder<ItemTrainingsGroupBinding, GroupType> =
        GroupViewHolder(createBinding(parent, ItemTrainingsGroupBinding::inflate))

    override fun getDiffUtil(): DiffUtil.ItemCallback<GroupType> = diffUtil

    private val diffUtil = object: DiffUtil.ItemCallback<GroupType>() {

        override fun areItemsTheSame(oldItem: GroupType, newItem: GroupType) =
            oldItem.programName == newItem.programName

        override fun areContentsTheSame(oldItem: GroupType, newItem: GroupType) =
            oldItem.programLevel == newItem.programLevel
                && oldItem.lastDate == newItem.lastDate
                && oldItem.programDescription == newItem.programDescription
                && oldItem.programImage == newItem.programImage
    }

    inner class GroupViewHolder(
        binding: ItemTrainingsGroupBinding
    ) : TrainingsViewHolder<ItemTrainingsGroupBinding, GroupType>(binding) {
        override fun onBind(item: GroupType) {
            with(binding) {
                programName.text = item.programName
                programDescription.text = item.programDescription
                lastTrainingDate.text = item.lastDate
                //TODO реализовать установку уровня сложности
                //TODO реализовать подстановку картинку программы
                root.setOnClickListener { item.onGroupClicked?.invoke() }
            }
        }
    }

    data class GroupType(
        val programLevel: Int,
        val programName: String,
        val programDescription: String,
        val lastDate: String,
        val programImage: String,
        val onGroupClicked: (() -> Unit)? = null
    ) : TrainingsAdapterType
}
