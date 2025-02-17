package com.openkin.hometraining.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import com.openkin.hometraining.BaseFragment
import com.openkin.hometraining.databinding.FragmentHomeBinding
import com.openkin.hometraining.ui.home.list.delegates.GoalsDelegate
import com.openkin.hometraining.ui.home.list.delegates.StatsDelegate

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val trainingsAdapter = TrainingsAdapter(getDelegates())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
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
            )
        ))
    }

    private fun openHistory() {
        Log.d("MyFilter", "Open history")
    }

    private fun openEditGoals() {
        Log.d("MyFilter", "Open edit goals")
    }

    private fun getDelegates() = listOf(StatsDelegate(), GoalsDelegate())
}
