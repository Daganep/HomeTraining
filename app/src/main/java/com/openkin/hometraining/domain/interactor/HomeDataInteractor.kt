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
        return Single.just(Goals(5, 0, listOf(), 3))
    }

    override fun getPrograms() : Single<List<ProgramSevenFour>> {
        return Single.just(listOf(
            ProgramSevenFour(
                "",
                0,
                0,
                "",
                "",)
        ))
    }

    override fun getMuscleGroups() : Single<List<MuscleGroup>> {
        return Single.just(listOf(
            MuscleGroup(
                "Пресс новичок",
                "20 мин · 16 упражнений",
                "Последний раз: окт.23.2024",
                TrainingLevel.BEGINNER,
                "url",
            ),
            MuscleGroup(
                "Руки новичок",
                "20 мин · 12 упражнений",
                "",
                TrainingLevel.BEGINNER,
                "url",
            ),
            MuscleGroup(
                "Плечи новичок",
                "20 мин · 16 упражнений",
                "15 мин · 10 упражнений",
                TrainingLevel.BEGINNER,
                "url",
            ),
            MuscleGroup(
                "Пресс новичок",
                "20 мин · 16 упражнений",
                "Последний раз: окт.23.2024",
                TrainingLevel.BEGINNER,
                "url",
            ),
            MuscleGroup(
                "Руки новичок",
                "20 мин · 12 упражнений",
                "",
                TrainingLevel.BEGINNER,
                "url",
            ),
            MuscleGroup(
                "Плечи новичок",
                "20 мин · 16 упражнений",
                "15 мин · 10 упражнений",
                TrainingLevel.BEGINNER,
                "url",
            ),
        ))
    }
}
