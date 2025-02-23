package com.openkin.hometraining.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.openkin.hometraining.ui.home.HomeScreenState.*
import com.openkin.hometraining.BaseFragment
import com.openkin.hometraining.R
import com.openkin.hometraining.databinding.FragmentHomeBinding
import com.openkin.hometraining.domain.model.Goals
import com.openkin.hometraining.domain.model.HomeStats
import com.openkin.hometraining.domain.model.MuscleGroup
import com.openkin.hometraining.domain.model.ProgramSevenFour
import com.openkin.hometraining.ui.home.list.ITabListener
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

    //Элементы экрана
    private var stats = StatsDelegate.StatsType()
    private var goals = GoalsDelegate.GoalsType()
    private lateinit var programTitle: CategoryTitleDelegate.CategoryTitleType
    private val programs = mutableListOf<ProgramsDelegate.ProgramsType>()
    private lateinit var categories: CategoriesDelegate.CategoriesType
    private val groups = mutableListOf<GroupDelegate.GroupType>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observeDataState()
    }

    private fun initUi() {
        binding?.homeScreenRecycler?.adapter = trainingsAdapter

        binding?.homeCategoryTitle?.categoryTitle?.text = getString(R.string.home_screen_titles_programs)
        setTabClickListener()
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

    private fun setTabClickListener() {
        binding?.homeTrainingsCategories?.root?.addOnTabSelectedListener(object : ITabListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> scrollToPosition(0)
                    1 -> scrollToPosition(2)
                    2 -> scrollToPosition(4)
                }
            }
        })
    }

    private fun updateList() {
        dataList.clear()
        //dataList.add(stats)
        //dataList.add(goals)
        //dataList.add(programTitle)
        //dataList.addAll(programs)
        //dataList.add(categories)
        dataList.addAll(groups)
        trainingsAdapter.setData(dataList)
    }

    private fun updateStats(statsData: HomeStats) {
        binding?.homeStats?.let {
            it.trainingsNumber.text = statsData.trainingNumber.toString()
            it.caloriesNumber.text = statsData.caloriesNumber.toString()
            it.minutesNumber.text = statsData.trainingsMinutes.toString()
            it.root.setOnClickListener { clickIndicator() }
        }
    }

    private fun updateGoals(goalsData: Goals) {
        binding?.homeGoals?.let {
            it.goalsDoneNumber.text = goalsData.goalsDone.toString()
            it.goalsWantedNumber.text = goalsData.goalsWanted.toString()
            //TODO добавить вставку строки из календаря
            it.editGoals.setOnClickListener { clickIndicator() }
            it.root.setOnClickListener { clickIndicator() }
        }
    }

    private fun updatePrograms(programsData: List<ProgramSevenFour>) {
//        programs.clear()
//        programsData.forEach {
//            programs.add(
//                ProgramsDelegate.ProgramsType(
//                    fullBodyProgramName =  it.programName,
//                    downBodyProgramName = it.programName,
//                    onBeginClicked = ::clickIndicator
//            ))
//        }
//        updateList()
    }

    private fun updateGroups(groupsData: List<MuscleGroup>) {
        groups.clear()
        groupsData.forEach {
            groups.add(
                GroupDelegate.GroupType(
                group = it,
                onGroupClicked = ::clickIndicator
            ))
        }
        updateList()
    }

    private fun scrollToPosition(position: Int, offset: Int = 0) {
        val layoutManager = binding?.homeScreenRecycler?.layoutManager as LinearLayoutManager
        layoutManager.scrollToPositionWithOffset(position, offset)
        binding?.mainAppbar?.setExpanded(false)
    }

    private fun clickIndicator(text: String = "") {
        Toast.makeText(activity, "Clicked$text", Toast.LENGTH_SHORT).show()
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
