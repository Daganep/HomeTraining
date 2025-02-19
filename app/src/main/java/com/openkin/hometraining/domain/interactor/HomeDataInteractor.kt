package com.openkin.hometraining.domain.interactor

import com.openkin.hometraining.domain.model.Goals
import com.openkin.hometraining.domain.model.HomeStats
import com.openkin.hometraining.domain.model.MuscleGroup
import com.openkin.hometraining.domain.model.ProgramSevenFour
import com.openkin.hometraining.domain.model.TrainingLevel
import io.reactivex.Single

class HomeDataInteractor : IHomeDataInteractor {

    override fun getStats() : Single<HomeStats> {
        return Single.just(HomeStats(0, 0,0))
    }

    override fun getGoals() : Single<Goals> {
        return Single.just(Goals(0, 0, listOf(), 0))
    }

    override fun getPrograms() : Single<List<ProgramSevenFour>> {
        return Single.just(listOf(ProgramSevenFour("", 0,0,"", "",)))
    }

    override fun getMuscleGroups() : Single<List<MuscleGroup>> {
        return Single.just(listOf(MuscleGroup("", "", "", TrainingLevel.BEGINNER)))
    }
}
