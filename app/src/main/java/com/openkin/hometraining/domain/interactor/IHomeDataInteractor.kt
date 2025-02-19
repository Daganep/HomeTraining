package com.openkin.hometraining.domain.interactor

import com.openkin.hometraining.domain.model.Goals
import com.openkin.hometraining.domain.model.HomeStats
import com.openkin.hometraining.domain.model.MuscleGroup
import com.openkin.hometraining.domain.model.ProgramSevenFour
import io.reactivex.Single

interface IHomeDataInteractor {
    fun getStats() : Single<HomeStats>
    fun getGoals() : Single<Goals>
    fun getPrograms() : Single<List<ProgramSevenFour>>
    fun getMuscleGroups() : Single<List<MuscleGroup>>
}
