package com.openkin.hometraining.ui.home

import com.openkin.hometraining.domain.model.Goals
import com.openkin.hometraining.domain.model.MuscleGroup
import com.openkin.hometraining.domain.model.ProgramSevenFour
import com.openkin.hometraining.domain.model.HomeStats

sealed interface HomeScreenState {

    data class StatsLoaded(val statsData: HomeStats) : HomeScreenState

    data class GoalsLoaded(val goalsData: Goals) : HomeScreenState

    data class ProgramsLoaded(val programsData: List<ProgramSevenFour>) : HomeScreenState

    data class GroupsLoaded(val groupsData: List<MuscleGroup>) : HomeScreenState

    data object LoadingState : HomeScreenState

    data class ErrorState(val message: String) : HomeScreenState
}
