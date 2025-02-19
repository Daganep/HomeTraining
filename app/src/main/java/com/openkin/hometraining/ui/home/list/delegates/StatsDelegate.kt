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
    StatsDelegate.StatsType> {

    override fun isRelative(item: TrainingsAdapterType) = item is StatsType

    override fun getLayoutId() = R.layout.item_trainings_stats

    override fun getViewHolder(
        parent: ViewGroup
    ): TrainingsViewHolder<ItemTrainingsStatsBinding, StatsType> =
        StatsViewHolder(createBinding(parent, ItemTrainingsStatsBinding::inflate))

    override fun getDiffUtil(): DiffUtil.ItemCallback<StatsType> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<StatsType>() {

        override fun areItemsTheSame(oldItem: StatsType, newItem: StatsType) = true

        override fun areContentsTheSame(oldItem: StatsType, newItem: StatsType) =
            oldItem.trainingsNumber == newItem.trainingsNumber
            && oldItem.caloriesNumber == newItem.caloriesNumber
            && oldItem.minutesNumber == newItem.minutesNumber
    }

    inner class StatsViewHolder(
        binding: ItemTrainingsStatsBinding
    ) : TrainingsViewHolder<ItemTrainingsStatsBinding, StatsType>(binding) {

        override fun onBind(item: StatsType) {
            with(binding) {
                trainingsNumber.text = item.trainingsNumber.toString()
                caloriesNumber.text = item.caloriesNumber.toString()
                minutesNumber.text = item.minutesNumber.toString()
                root.setOnClickListener { item.onStatsClicked?.invoke() }
            }
        }
    }

    data class StatsType(
        var trainingsNumber: Int,
        var caloriesNumber: Int,
        var minutesNumber: Int,
        val onStatsClicked: (() -> Unit)? = null
    ) : TrainingsAdapterType
}
