package com.openkin.hometraining.ui.home.list

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

internal abstract class TrainingsViewHolder<out V : ViewBinding, in I : TrainingsAdapterType> (
    val binding: V
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(item: I)
}
