package com.openkin.hometraining.ui.record

import com.openkin.hometraining.domain.model.Goals
import com.openkin.hometraining.domain.model.MuscleGroup
import com.openkin.hometraining.domain.model.ProgramSevenFour
import com.openkin.hometraining.domain.model.HomeStats

sealed interface RecordScreenState {

    data class StatsLoaded(val statsData: HomeStats) : RecordScreenState

    data class GoalsLoaded(val goalsData: Goals) : RecordScreenState

    data class ProgramsSevenFourLoaded(val programsData: List<ProgramSevenFour>) : RecordScreenState

    data class ProgramsGroupsLoaded(val groupsData: List<MuscleGroup>) : RecordScreenState

    data object LoadingState : RecordScreenState

    data class ErrorState(val message: String) : RecordScreenState
}
