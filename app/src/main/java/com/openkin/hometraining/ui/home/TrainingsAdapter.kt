package com.openkin.hometraining.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.openkin.hometraining.ui.home.list.TrainingsDelegate
import com.openkin.hometraining.ui.home.list.TrainingsAdapterType
import com.openkin.hometraining.ui.home.list.TrainingsViewHolder

internal class TrainingsAdapter(
    private val delegates: List<TrainingsDelegate<*, *>>
) : RecyclerView.Adapter<TrainingsViewHolder<ViewBinding, TrainingsAdapterType>>() {

    private val data: MutableList<TrainingsAdapterType> = mutableListOf()

    fun setData(newData: List<TrainingsAdapterType>) {
        val diffUtil = TrainingsDiffUtils(delegates, data, newData)
        val difResult = DiffUtil.calculateDiff(diffUtil)
        data.clear()
        data.addAll(newData)
        difResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrainingsViewHolder<ViewBinding, TrainingsAdapterType> =
        delegates.find { it.getLayoutId() == viewType }
            ?.getViewHolder(parent)
            ?.let { it as TrainingsViewHolder<ViewBinding, TrainingsAdapterType> }
            ?: throw IllegalArgumentException()

    override fun getItemViewType(position: Int): Int =
        delegates.find { it.isRelative(data[position]) }
            ?.getLayoutId()
            ?: throw IllegalArgumentException()

    override fun getItemCount() = data.count()

    override fun onBindViewHolder(
        holder: TrainingsViewHolder<ViewBinding, TrainingsAdapterType>, position: Int
    ) {
        holder.onBind(data[position])
    }

    private class TrainingsDiffUtils(
        private val delegates: List<TrainingsDelegate<*, *>>,
        private val oldData: List<TrainingsAdapterType>,
        private val newData: List<TrainingsAdapterType>,
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldData.count()

        override fun getNewListSize() = newData.count()

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            if (oldData[oldItemPosition]::class != newData[newItemPosition]::class) return false
            return getCallback(oldData[oldItemPosition])
                .areItemsTheSame(
                    oldData[oldItemPosition],
                    newData[newItemPosition]
                )
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            getCallback(oldData[oldItemPosition])
                .areContentsTheSame(
                    oldData[oldItemPosition],
                    newData[newItemPosition]
                )

        private fun getCallback(
            item: TrainingsAdapterType
        ) : DiffUtil.ItemCallback<TrainingsAdapterType> =
            delegates.find { it.isRelative(item) }
                ?.getDiffUtil()
                ?.let { it as DiffUtil.ItemCallback<TrainingsAdapterType> }
                ?: throw IllegalStateException()
    }

}
