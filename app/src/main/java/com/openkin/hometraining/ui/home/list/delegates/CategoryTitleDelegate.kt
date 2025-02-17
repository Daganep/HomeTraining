package com.openkin.hometraining.ui.home.list.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.openkin.hometraining.R
import com.openkin.hometraining.createBinding
import com.openkin.hometraining.databinding.ItemTrainingsCategoryTitleBinding
import com.openkin.hometraining.ui.home.list.TrainingsAdapterType
import com.openkin.hometraining.ui.home.list.TrainingsDelegate
import com.openkin.hometraining.ui.home.list.TrainingsViewHolder

internal class CategoryTitleDelegate : TrainingsDelegate<ItemTrainingsCategoryTitleBinding,
    CategoryTitleDelegate.CategoryTitleType> {

    override fun isRelative(item: TrainingsAdapterType) = item is CategoryTitleType

    override fun getLayoutId() = R.layout.item_trainings_category_title

    override fun getViewHolder(
        parent: ViewGroup
    ): TrainingsViewHolder<ItemTrainingsCategoryTitleBinding, CategoryTitleType> =
        CategoryTitleViewHolder(createBinding(parent, ItemTrainingsCategoryTitleBinding::inflate))

    override fun getDiffUtil(): DiffUtil.ItemCallback<CategoryTitleType> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<CategoryTitleType>() {
        override fun areItemsTheSame(oldItem: CategoryTitleType, newItem: CategoryTitleType) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: CategoryTitleType, newItem: CategoryTitleType) =
            oldItem.titleLevel == newItem.titleLevel
    }

    inner class CategoryTitleViewHolder(
        binding: ItemTrainingsCategoryTitleBinding
    ) : TrainingsViewHolder<ItemTrainingsCategoryTitleBinding, CategoryTitleType>(binding) {

        override fun onBind(item: CategoryTitleType) {
            binding.categoryTitle.text = item.title
            //TODO реализовать изменение размера заголовка в зависимости от уровня
        }
    }

    data class CategoryTitleType(val title: String, val titleLevel: Int) : TrainingsAdapterType
}
