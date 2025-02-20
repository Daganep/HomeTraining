package com.openkin.hometraining.ui.home.list.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.openkin.hometraining.R
import com.openkin.hometraining.createBinding
import com.openkin.hometraining.databinding.ItemTrainingsGoalBinding
import com.openkin.hometraining.domain.model.Goals
import com.openkin.hometraining.ui.home.list.TrainingsAdapterType
import com.openkin.hometraining.ui.home.list.TrainingsDelegate
import com.openkin.hometraining.ui.home.list.TrainingsViewHolder

internal class GoalsDelegate: TrainingsDelegate<ItemTrainingsGoalBinding, GoalsDelegate.GoalsType> {

    override fun isRelative(item: TrainingsAdapterType) = item is GoalsType

    override fun getLayoutId() = R.layout.item_trainings_goal

    override fun getViewHolder(
        parent: ViewGroup
    ): TrainingsViewHolder<ItemTrainingsGoalBinding, GoalsType> =
        GoalsViewHolder(createBinding(parent, ItemTrainingsGoalBinding::inflate))

    override fun getDiffUtil(): DiffUtil.ItemCallback<GoalsType> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<GoalsType>() {

        override fun areItemsTheSame(oldItem: GoalsType, newItem: GoalsType) = true

        override fun areContentsTheSame(oldItem: GoalsType, newItem: GoalsType) =
            oldItem.goals.goalsDone == newItem.goals.goalsDone
            && oldItem.goals.goalsWanted == newItem.goals.goalsWanted
            && oldItem.goals.currentDayNumber == newItem.goals.currentDayNumber
    }

    inner class GoalsViewHolder(
        binding: ItemTrainingsGoalBinding
    ) : TrainingsViewHolder<ItemTrainingsGoalBinding, GoalsType>(binding) {
        override fun onBind(item: GoalsType) {
            with(binding) {
                goalsDoneNumber.text = item.goals.goalsDone.toString()
                goalsWantedNumber.text = item.goals.goalsWanted.toString()
                //TODO добавить вставку строки из календаря
                editGoals.setOnClickListener { item.onGoalsEdit?.invoke() }
                root.setOnClickListener { item.onGoalsClicked?.invoke() }
            }
        }
    }

    data class GoalsType(
        val goals: Goals = Goals(0, 0, listOf(), 0),
        val onGoalsEdit: (() -> Unit)? = null,
        val onGoalsClicked: (() -> Unit)? = null,
    ) : TrainingsAdapterType
}
