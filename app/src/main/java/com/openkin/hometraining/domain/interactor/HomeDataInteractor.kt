package com.openkin.hometraining.domain.interactor

import com.openkin.hometraining.domain.model.Goals
import com.openkin.hometraining.domain.model.HomeStats
import com.openkin.hometraining.domain.model.MuscleGroup
import com.openkin.hometraining.domain.model.ProgramSevenFour
import com.openkin.hometraining.domain.model.TrainingLevel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class HomeDataInteractor : IHomeDataInteractor {

    override fun getStats() : Single<HomeStats> {
        return Single.just(HomeStats(32, 6808,303))
            .subscribeOn(Schedulers.io())
    }

    override fun getGoals() : Single<Goals> {
        return Single.just(Goals(5, 0, listOf(), 3))
            .subscribeOn(Schedulers.io())
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
            .subscribeOn(Schedulers.io())
    }

    override fun getMuscleGroups() : Single<List<MuscleGroup>> {
        return Single.just(listOf(
            MuscleGroup(
                "Пресс новичок",
                "20 мин · 16 упражнений",
                "Последний раз: окт.23.2024",
                TrainingLevel.BEGINNER,
                "android.resource://com.openkin.hometraining/drawable/image_program_press_beginner",
            ),
            MuscleGroup(
                "Руки новичок",
                "20 мин · 12 упражнений",
                "",
                TrainingLevel.BEGINNER,
                "",
            ),
            MuscleGroup(
                "Плечи новичок",
                "20 мин · 16 упражнений",
                "15 мин · 10 упражнений",
                TrainingLevel.BEGINNER,
                "android.resource://com.openkin.hometraining/drawable/image_program_press_beginner",
            ),
            MuscleGroup(
                "Пресс новичок",
                "20 мин · 16 упражнений",
                "Последний раз: окт.23.2024",
                TrainingLevel.BEGINNER,
                "",
            ),
            MuscleGroup(
                "Руки новичок",
                "20 мин · 12 упражнений",
                "",
                TrainingLevel.BEGINNER,
                "android.resource://com.openkin.hometraining/drawable/image_program_press_beginner",
            ),
            MuscleGroup(
                "Плечи новичок",
                "20 мин · 16 упражнений",
                "15 мин · 10 упражнений",
                TrainingLevel.BEGINNER,
                "",
            ),
        ))
            .subscribeOn(Schedulers.io())
    }
}
