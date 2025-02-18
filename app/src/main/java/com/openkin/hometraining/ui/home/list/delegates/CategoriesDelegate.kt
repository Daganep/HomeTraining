package com.openkin.hometraining.ui.home.list.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.openkin.hometraining.R
import com.openkin.hometraining.createBinding
import com.openkin.hometraining.databinding.ItemTrainingsCategoriesBinding
import com.openkin.hometraining.ui.home.list.TrainingsAdapterType
import com.openkin.hometraining.ui.home.list.TrainingsDelegate
import com.openkin.hometraining.ui.home.list.TrainingsViewHolder

internal class CategoriesDelegate : TrainingsDelegate<ItemTrainingsCategoriesBinding,
    CategoriesDelegate.CategoriesType> {

    override fun isRelative(item: TrainingsAdapterType) = item is CategoriesType

    override fun getLayoutId() = R.layout.item_trainings_categories

    override fun getViewHolder(parent: ViewGroup) =
        CategoriesViewHolder(createBinding(parent, ItemTrainingsCategoriesBinding::inflate))

    override fun getDiffUtil(): DiffUtil.ItemCallback<CategoriesType> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<CategoriesType>() {
        override fun areItemsTheSame(oldItem: CategoriesType, newItem: CategoriesType) = true

        override fun areContentsTheSame(oldItem: CategoriesType, newItem: CategoriesType) = true
    }

    inner class CategoriesViewHolder(
        binding: ItemTrainingsCategoriesBinding
    ) : TrainingsViewHolder<ItemTrainingsCategoriesBinding, CategoriesType>(binding) {
        override fun onBind(item: CategoriesType) {
            with(binding) {
                beginnerProgram.setOnClickListener { item.onBeginnerClicked?.invoke() }
                advancedProgram.setOnClickListener { item.onAdvancedClicked?.invoke() }
                profProgram.setOnClickListener { item.onProfClicked?.invoke() }
            }
        }
    }

    data class CategoriesType(
        val onBeginnerClicked: (() -> Unit)? = null,
        val onAdvancedClicked: (() -> Unit)? = null,
        val onProfClicked: (() -> Unit)? = null,
    ) : TrainingsAdapterType
}
