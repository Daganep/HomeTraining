package com.openkin.hometraining.ui.home.list

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding

internal interface TrainingsDelegate<V: ViewBinding, I : TrainingsAdapterType> {

    fun isRelative(item: TrainingsAdapterType): Boolean

    @LayoutRes
    fun getLayoutId(): Int

    fun getViewHolder(parent: ViewGroup): TrainingsViewHolder<V, I>

    fun getDiffUtil(): DiffUtil.ItemCallback<I>
}
