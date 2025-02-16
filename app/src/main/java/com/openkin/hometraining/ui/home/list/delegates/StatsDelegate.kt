package com.openkin.hometraining.ui.home.list.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.openkin.hometraining.R
import com.openkin.hometraining.createBinding
import com.openkin.hometraining.databinding.ItemTrainingsStatsBinding
import com.openkin.hometraining.ui.home.list.TrainingsAdapterType
import com.openkin.hometraining.ui.home.list.TrainingsDelegate
import com.openkin.hometraining.ui.home.list.TrainingsViewHolder

internal class StatsDelegate: TrainingsDelegate<ItemTrainingsStatsBinding,
    StatsDelegate.StatsAdapterType> {

    override fun isRelative(item: TrainingsAdapterType) = item is StatsAdapterType

    override fun getLayoutId() = R.layout.item_trainings_stats

    override fun getViewHolder(
        parent: ViewGroup
    ): TrainingsViewHolder<ItemTrainingsStatsBinding, StatsAdapterType> =
        StatsViewHolder(createBinding(parent, ItemTrainingsStatsBinding::inflate))

    override fun getDiffUtil(): DiffUtil.ItemCallback<StatsAdapterType> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<StatsAdapterType>() {

        override fun areItemsTheSame(oldItem: StatsAdapterType, newItem: StatsAdapterType) = true

        override fun areContentsTheSame(oldItem: StatsAdapterType, newItem: StatsAdapterType) =
            oldItem.trainingsNumber == newItem.trainingsNumber
            && oldItem.caloriesNumber == newItem.caloriesNumber
            && oldItem.minutesNumber == newItem.minutesNumber
    }

    inner class StatsViewHolder(
        binding: ItemTrainingsStatsBinding
    ) : TrainingsViewHolder<ItemTrainingsStatsBinding, StatsAdapterType>(binding) {

        override fun onBind(item: StatsAdapterType) {
            with(binding) {
                trainingsNumber.text = item.trainingsNumber.toString()
                caloriesNumber.text = item.caloriesNumber.toString()
                minutesNumber.text = item.minutesNumber.toString()
            }
        }
    }

    data class StatsAdapterType(
        var trainingsNumber: Int,
        var caloriesNumber: Int,
        var minutesNumber: Int,
        val onStatsClicked: (() -> Unit)?
    ) : TrainingsAdapterType
}
