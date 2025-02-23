package com.openkin.hometraining.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.tabs.TabLayout
import com.openkin.hometraining.ui.home.HomeScreenState.*
import com.openkin.hometraining.BaseFragment
import com.openkin.hometraining.R
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
//        programTitle = CategoryTitleDelegate.CategoryTitleType(
//            title = getString(R.string.home_screen_titles_programs),
//            titleLevel = 1
//        )

//        categories = CategoriesDelegate.CategoriesType(
//            ::clickIndicator,
//            ::clickIndicator,
//            ::clickIndicator,
//        )
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
        binding?.homeTrainingsCategories?.root?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                clickIndicator(" ${tab?.position}")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

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
//        stats = StatsDelegate.StatsType(
//            stats = statsData,
//            onStatsClicked = ::clickIndicator,
//        )
//        updateList()
    }

    private fun updateGoals(goalsData: Goals) {
        binding?.homeGoals?.let {
            it.goalsDoneNumber.text = goalsData.goalsDone.toString()
            it.goalsWantedNumber.text = goalsData.goalsWanted.toString()
            //TODO добавить вставку строки из календаря
            it.editGoals.setOnClickListener { clickIndicator() }
            it.root.setOnClickListener { clickIndicator() }
        }
//        goals = GoalsDelegate.GoalsType(
//            goals = goalsData,
//            onGoalsEdit = ::clickIndicator,
//            onGoalsClicked = ::clickIndicator,
//        )
//        updateList()
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
