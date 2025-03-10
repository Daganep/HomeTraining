package com.openkin.hometraining.ui.home.list.delegates

import android.net.Uri
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.openkin.hometraining.R
import com.openkin.hometraining.createBinding
import com.openkin.hometraining.databinding.ItemTrainingsGroupBinding
import com.openkin.hometraining.domain.model.MuscleGroup
import com.openkin.hometraining.domain.model.TrainingLevel
import com.openkin.hometraining.ui.home.list.TrainingsAdapterType
import com.openkin.hometraining.ui.home.list.TrainingsDelegate
import com.openkin.hometraining.ui.home.list.TrainingsViewHolder

internal class ProgramGroupDelegate : TrainingsDelegate<ItemTrainingsGroupBinding,
    ProgramGroupDelegate.GroupType> {

    override fun isRelative(item: TrainingsAdapterType) = item is GroupType

    override fun getLayoutId() = R.layout.item_trainings_group

    override fun getViewHolder(
        parent: ViewGroup
    ): TrainingsViewHolder<ItemTrainingsGroupBinding, GroupType> =
        GroupViewHolder(createBinding(parent, ItemTrainingsGroupBinding::inflate))

    override fun getDiffUtil(): DiffUtil.ItemCallback<GroupType> = diffUtil

    private val diffUtil = object: DiffUtil.ItemCallback<GroupType>() {

        override fun areItemsTheSame(oldItem: GroupType, newItem: GroupType) =
            oldItem.group.groupName == newItem.group.groupName

        override fun areContentsTheSame(oldItem: GroupType, newItem: GroupType) =
            oldItem.group.level == newItem.group.level
                && oldItem.group.lastDate == newItem.group.lastDate
                && oldItem.group.description == newItem.group.description
                && oldItem.group.groupImage == newItem.group.groupImage
    }

    inner class GroupViewHolder(
        binding: ItemTrainingsGroupBinding
    ) : TrainingsViewHolder<ItemTrainingsGroupBinding, GroupType>(binding) {
        override fun onBind(item: GroupType) {
            with(binding) {
                programName.text = item.group.groupName
                programDescription.text = item.group.description
                lastTrainingDate.text = item.group.lastDate
                programLevel.load(getProgramLevelUri(item.group.level)) //TODO присылать готовый Uri, а не парсить здесь
                val uri = Uri.parse(item.group.groupImage) //TODO присылать готовый Uri, а не парсить здесь
                binding.programImage.load(uri)
                root.setOnClickListener { item.onGroupClicked?.invoke() }
            }
        }

        private fun getProgramLevelUri(level: TrainingLevel): Uri {
            val programLevel: String = when(level) {
                TrainingLevel.BEGINNER -> BEGINNER
                TrainingLevel.CONTINUE -> CONTINUE
                TrainingLevel.ADVANCED -> ADVANCED
            }
            return Uri.parse(BASE_LEVEL_URL + programLevel)
        }
    }

    data class GroupType(
        val group: MuscleGroup = MuscleGroup(
            groupName = "",
            description = "",
            lastDate = "",
            level = TrainingLevel.BEGINNER,
            groupImage = "",
        ),
        val onGroupClicked: (() -> Unit)? = null
    ) : TrainingsAdapterType

    companion object {
        private const val BASE_LEVEL_URL = "android.resource://com.openkin.hometraining/drawable/"
        private const val BEGINNER = "image_program_level_beginner"
        private const val CONTINUE = "image_program_level_continue"
        private const val ADVANCED = "image_program_level_advanced"
    }
}
