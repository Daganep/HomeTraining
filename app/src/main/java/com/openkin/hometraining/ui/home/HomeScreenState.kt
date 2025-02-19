package com.openkin.hometraining.ui.home

import com.openkin.hometraining.domain.model.Goals
import com.openkin.hometraining.domain.model.MuscleGroup
import com.openkin.hometraining.domain.model.ProgramSevenFour
import com.openkin.hometraining.domain.model.HomeStats

sealed interface HomeScreenState {

    data class StatsLoaded(val statsData: HomeStats) : HomeScreenState

    data class GoalsLoaded(val statsData: Goals) : HomeScreenState

    data class ProgramsLoaded(val statsData: List<ProgramSevenFour>) : HomeScreenState

    data class GroupsLoaded(val statsData: List<MuscleGroup>) : HomeScreenState

    data object LoadingState : HomeScreenState

    data object ErrorState : HomeScreenState
}
