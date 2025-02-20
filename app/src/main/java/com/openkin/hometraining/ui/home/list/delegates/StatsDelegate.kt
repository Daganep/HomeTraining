package com.openkin.hometraining.ui.home.list.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.openkin.hometraining.R
import com.openkin.hometraining.createBinding
import com.openkin.hometraining.databinding.ItemTrainingsStatsBinding
import com.openkin.hometraining.domain.model.HomeStats
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
            oldItem.stats.trainingNumber == newItem.stats.trainingNumber
            && oldItem.stats.caloriesNumber == newItem.stats.caloriesNumber
            && oldItem.stats.trainingsMinutes == newItem.stats.trainingsMinutes
    }

    inner class StatsViewHolder(
        binding: ItemTrainingsStatsBinding
    ) : TrainingsViewHolder<ItemTrainingsStatsBinding, StatsType>(binding) {

        override fun onBind(item: StatsType) {
            with(binding) {
                trainingsNumber.text = item.stats.trainingNumber.toString()
                caloriesNumber.text = item.stats.caloriesNumber.toString()
                minutesNumber.text = item.stats.trainingsMinutes.toString()
                root.setOnClickListener { item.onStatsClicked?.invoke() }
            }
        }
    }

    data class StatsType(
        var stats: HomeStats = HomeStats(0, 0, 0),
        val onStatsClicked: (() -> Unit)? = null
    ) : TrainingsAdapterType
}
