package com.openkin.hometraining.domain.interactor

import com.openkin.hometraining.domain.model.Goals
import com.openkin.hometraining.domain.model.HomeStats
import com.openkin.hometraining.domain.model.MuscleGroup
import com.openkin.hometraining.domain.model.ProgramSevenFour
import io.reactivex.Single

interface IHomeDataRepository {
    fun getStats() : Single<HomeStats>
    fun getGoals() : Single<Goals>
    fun getProgramsSevenFour() : Single<List<ProgramSevenFour>>
    fun getProgramsGroups() : Single<List<MuscleGroup>>
}
