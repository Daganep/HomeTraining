package com.openkin.hometraining.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.openkin.hometraining.ui.home.HomeScreenState.*
import com.openkin.hometraining.BaseFragment
import com.openkin.hometraining.databinding.FragmentHomeBinding
import com.openkin.hometraining.domain.model.Goals
import com.openkin.hometraining.domain.model.HomeStats
import com.openkin.hometraining.domain.model.MuscleGroup
import com.openkin.hometraining.domain.model.ProgramSevenFour
import com.openkin.hometraining.ui.home.list.TrainingsAdapterType
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
    private val dataList = mutableListOf<TrainingsAdapterType>()
    private var stats = StatsDelegate.StatsType(0, 0,0)
    private var goals = GoalsDelegate.GoalsType(0, 0, listOf(), 0)
    private val programs = mutableListOf<ProgramsDelegate.ProgramsType>()
    private val groups = mutableListOf<GroupDelegate.GroupType>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observeDataState()
    }

    private fun initUi() {
        binding?.homeScreenRecycler?.adapter = trainingsAdapter
    }

    private fun observeDataState() {
        viewModel.homeScreenData.observe(viewLifecycleOwner) { state ->
            when(state) {
                is StatsLoaded -> updateStats(state.statsData)
                is GoalsLoaded -> updateGoals(state.goalsData)
                is ProgramsLoaded -> updatePrograms(state.programsData)
                is GroupsLoaded -> updateGroups(state.groupsData)
                is LoadingState -> Log.d("MyFilter", "Loading in progress!")
                is ErrorState -> Log.e("Errors", state.message)
            }
        }
    }

    private fun updateList() {
        dataList.clear()
        dataList.add(stats)
        dataList.add(goals)
        dataList.add(
            CategoryTitleDelegate.CategoryTitleType(
            title = "ПРОГРАММА кек пек",
            titleLevel = 1,
        ))
        dataList.addAll(programs)
        dataList.add(
            CategoriesDelegate.CategoriesType(
                ::clickIndicator,
                ::clickIndicator,
                ::clickIndicator,
            ))
        dataList.addAll(groups)
        trainingsAdapter.setData(dataList)
    }

    private fun updateStats(statsData: HomeStats) {
        Log.d("MyFilter", statsData.toString())
        stats = StatsDelegate.StatsType(
            trainingsNumber = statsData.trainingNumber,
            caloriesNumber = statsData.caloriesNumber,
            minutesNumber = statsData.trainingsMinutes,
            onStatsClicked = ::clickIndicator,
        )
        updateList()
    }

    private fun updateGoals(goalsData: Goals) {
        Log.d("MyFilter", goalsData.toString() )
        goals = GoalsDelegate.GoalsType(
            goalsDone = goalsData.goalsDone,
            goalsWanted = goalsData.goalsWanted,
            currentWeek = goalsData.currentWeak,
            currentDay = goalsData.currentDayNumber,
            onGoalsEdit = ::clickIndicator,
            onGoalsClicked = ::clickIndicator,
        )
        updateList()
    }

    private fun updatePrograms(programsData: List<ProgramSevenFour>) {
        Log.d("MyFilter", programsData.toString())
        programs.clear()
        //TODO добавлять программы в цикле
        updateList()
    }

    private fun updateGroups(groupsData: List<MuscleGroup>) {
        Log.d("MyFilter", groupsData.toString())
        programs.clear()
        //TODO добавлять программы по группам мышц в цикле
        updateList()
    }

    private fun clickIndicator() {
        Toast.makeText(activity, "Clicked", Toast.LENGTH_LONG).show()
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
