package com.openkin.hometraining.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import com.openkin.hometraining.ui.home.HomeScreenState.*
import com.openkin.hometraining.BaseFragment
import com.openkin.hometraining.databinding.FragmentHomeBinding
import com.openkin.hometraining.ui.home.list.delegates.CategoriesDelegate
import com.openkin.hometraining.ui.home.list.delegates.CategoryTitleDelegate
import com.openkin.hometraining.ui.home.list.delegates.GoalsDelegate
import com.openkin.hometraining.ui.home.list.delegates.GroupDelegate
import com.openkin.hometraining.ui.home.list.delegates.ProgramsDelegate
import com.openkin.hometraining.ui.home.list.delegates.StatsDelegate
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModel<HomeViewModel>()
    private val trainingsAdapter = TrainingsAdapter(getDelegates())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observeDataState()
    }

    private fun initUi() {
        binding?.homeScreenRecycler?.adapter = trainingsAdapter
        trainingsAdapter.setData(listOf(
            StatsDelegate.StatsType(
                trainingsNumber = 32,
                caloriesNumber = 6808,
                minutesNumber = 303,
                onStatsClicked = ::openHistory
            ),
            GoalsDelegate.GoalsType(
                goalsDone = 0,
                goalsWanted = 5,
                currentWeek =  listOf(),
                currentDay = 3,
                onGoalsEdit = ::openEditGoals,
                onGoalsClicked = ::openHistory,
            ),
            CategoryTitleDelegate.CategoryTitleType(
                title = "ПРОГРАММА кек пек",
                titleLevel = 1,
            ),
            ProgramsDelegate.ProgramsType(
                fullBodyProgramName = "",
                downBodyProgramName = "",
                onBeginClicked = null
            ),
            CategoriesDelegate.CategoriesType(
                ::openHistory,
                ::openHistory,
                ::openHistory,
            ),
            GroupDelegate.GroupType(
                programLevel = 1,
                programName = "Пресс новичок",
                programDescription = "20 мин · 16 упражнений",
                lastDate = "Последний раз: окт.23.2024",
                programImage = "press beginner",
                onGroupClicked = ::openHistory
            ),
            GroupDelegate.GroupType(
                programLevel = 1,
                programName = "Руки новичок",
                programDescription = "20 мин · 12 упражнений",
                lastDate = "",
                programImage = "press beginner"
            ),
            GroupDelegate.GroupType(
                programLevel = 1,
                programName = "Плечи новичок",
                programDescription = "15 мин · 10 упражнений",
                lastDate = "Последний раз: янв.15.2025",
                programImage = "press beginner"
            ),
            CategoryTitleDelegate.CategoryTitleType(
                title = "Продолжающий",
                titleLevel = 1,
            ),
            GroupDelegate.GroupType(
                programLevel = 1,
                programName = "Пресс новичок",
                programDescription = "20 мин · 16 упражнений",
                lastDate = "Последний раз: окт.23.2024",
                programImage = "press beginner"
            ),
            GroupDelegate.GroupType(
                programLevel = 1,
                programName = "Руки новичок",
                programDescription = "20 мин · 12 упражнений",
                lastDate = "",
                programImage = "press beginner"
            ),
            GroupDelegate.GroupType(
                programLevel = 1,
                programName = "Плечи новичок",
                programDescription = "15 мин · 10 упражнений",
                lastDate = "Последний раз: янв.15.2025",
                programImage = "press beginner"
            ),
        ))
    }

    private fun observeDataState() {
        viewModel.homeScreenData.observe(viewLifecycleOwner) { state ->
            when(state) {
                is StatsLoaded -> { }
                is GoalsLoaded -> { }
                is ProgramsLoaded -> { }
                is GroupsLoaded -> { }
                is LoadingState -> { }
                is ErrorState -> { }
            }
        }
    }

    private fun openHistory() {
        Log.d("MyFilter", "Open history")
    }

    private fun openEditGoals() {
        Log.d("MyFilter", "Open edit goals")
    }

    private fun getDelegates() = listOf(
        StatsDelegate(),
        GoalsDelegate(),
        CategoryTitleDelegate(),
        ProgramsDelegate(),
        CategoriesDelegate(),
        GroupDelegate(),
    )
}
