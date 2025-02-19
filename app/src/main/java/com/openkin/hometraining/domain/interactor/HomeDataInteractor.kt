package com.openkin.hometraining.domain.interactor

import com.openkin.hometraining.domain.model.Goals
import com.openkin.hometraining.domain.model.HomeStats
import com.openkin.hometraining.domain.model.MuscleGroup
import com.openkin.hometraining.domain.model.ProgramSevenFour
import com.openkin.hometraining.domain.model.TrainingLevel
import io.reactivex.Single

class HomeDataInteractor : IHomeDataInteractor {

    override fun getStats() : Single<HomeStats> {
        return Single.just(HomeStats(32, 6808,303))
    }

    override fun getGoals() : Single<Goals> {
        return Single.just(Goals(0, 5, listOf(), 3))
    }

    override fun getPrograms() : Single<List<ProgramSevenFour>> {
        return Single.just(listOf(ProgramSevenFour("", 0,0,"", "",)))
    }

    override fun getMuscleGroups() : Single<List<MuscleGroup>> {
        return Single.just(listOf(MuscleGroup("", "", "", TrainingLevel.BEGINNER)))
    }
}

//trainingsAdapter.setData(listOf(
//ProgramsDelegate.ProgramsType(
//fullBodyProgramName = "",
//downBodyProgramName = "",
//onBeginClicked = null
//),
//GroupDelegate.GroupType(
//programLevel = 1,
//programName = "Пресс новичок",
//programDescription = "20 мин · 16 упражнений",
//lastDate = "Последний раз: окт.23.2024",
//programImage = "press beginner",
//onGroupClicked = ::clickIndicator
//),
//GroupDelegate.GroupType(
//programLevel = 1,
//programName = "Руки новичок",
//programDescription = "20 мин · 12 упражнений",
//lastDate = "",
//programImage = "press beginner"
//),
//GroupDelegate.GroupType(
//programLevel = 1,
//programName = "Плечи новичок",
//programDescription = "15 мин · 10 упражнений",
//lastDate = "Последний раз: янв.15.2025",
//programImage = "press beginner"
//),
//CategoryTitleDelegate.CategoryTitleType(
//title = "Продолжающий",
//titleLevel = 1,
//),
//GroupDelegate.GroupType(
//programLevel = 1,
//programName = "Пресс новичок",
//programDescription = "20 мин · 16 упражнений",
//lastDate = "Последний раз: окт.23.2024",
//programImage = "press beginner"
//),
//GroupDelegate.GroupType(
//programLevel = 1,
//programName = "Руки новичок",
//programDescription = "20 мин · 12 упражнений",
//lastDate = "",
//programImage = "press beginner"
//),
//GroupDelegate.GroupType(
//programLevel = 1,
//programName = "Плечи новичок",
//programDescription = "15 мин · 10 упражнений",
//lastDate = "Последний раз: янв.15.2025",
//programImage = "press beginner"
//),
//))
