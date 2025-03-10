package com.openkin.hometraining.domain.interactor

import com.openkin.hometraining.domain.model.Goals
import com.openkin.hometraining.domain.model.HomeStats
import com.openkin.hometraining.domain.model.MuscleGroup
import com.openkin.hometraining.domain.model.ProgramSevenFour
import io.reactivex.Single

class HomeDataInteractor(
    private val homeDataRepository: IHomeDataRepository
) : IHomeDataInteractor {

    override fun getStats() : Single<HomeStats> {
        return homeDataRepository.getStats()
    }

    override fun getGoals() : Single<Goals> {
        return homeDataRepository.getGoals()
    }

    override fun getProgramsSevenFour() : Single<List<ProgramSevenFour>> {
        return homeDataRepository.getProgramsSevenFour()
    }

    override fun getProgramsGroups() : Single<List<MuscleGroup>> {
        return homeDataRepository.getProgramsGroups()
    }
}
