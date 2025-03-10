package com.openkin.hometraining.data

import android.net.Uri
import com.openkin.hometraining.domain.interactor.IHomeDataRepository
import com.openkin.hometraining.domain.model.Goals
import com.openkin.hometraining.domain.model.HomeStats
import com.openkin.hometraining.domain.model.MuscleGroup
import com.openkin.hometraining.domain.model.ProgramSevenFour
import com.openkin.hometraining.domain.model.TrainingLevel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class HomeDataRepository : IHomeDataRepository {
    override fun getStats(): Single<HomeStats> {
        return Single.just(HomeStats(32, 6808,303))
            .subscribeOn(Schedulers.io())
    }

    override fun getGoals(): Single<Goals> {
        return Single.just(Goals(5, 2, listOf(24, 25, 26, 27, 28, 1, 2), 0))
            .subscribeOn(Schedulers.io())
    }

    override fun getProgramsSevenFour(): Single<List<ProgramSevenFour>> {
        return Single.just(listOf(
            ProgramSevenFour(
                "Kek pek",
                50,
                4,
                "",
                Uri.parse("android.resource://com.openkin.hometraining/drawable/image_program_full_7x4")),
            ProgramSevenFour(
                "Keker",
                0,
                0,
                "Всего за 4 недели укрепите ноги, увеличьте силу нижней части тела и улучшите свою общую физическую подготовку",
                Uri.parse("android.resource://com.openkin.hometraining/drawable/image_program_down_7x4"),)
        ))
            .subscribeOn(Schedulers.io())
    }

    override fun getProgramsGroups(): Single<List<MuscleGroup>> {
        return Single.just(listOf(
            MuscleGroup(
                "Пресс новичок",
                "20 мин · 16 упражнений",
                "Последний раз: окт.23.2024",
                TrainingLevel.BEGINNER,
                "android.resource://com.openkin.hometraining/drawable/image_program_press_beginner",
            ),
            MuscleGroup(
                "Плечи и спина новичок",
                "20 мин · 16 упражнений",
                "15 мин · 10 упражнений",
                TrainingLevel.BEGINNER,
                "android.resource://com.openkin.hometraining/drawable/image_program_shoulders_beginner",
            ),
            MuscleGroup(
                "Руки новичок",
                "20 мин · 16 упражнений",
                "15 мин · 10 упражнений",
                TrainingLevel.BEGINNER,
                "android.resource://com.openkin.hometraining/drawable/image_program_arm_beginner",
            ),
            MuscleGroup(
                "Ноги новичок",
                "20 мин · 12 упражнений",
                "",
                TrainingLevel.BEGINNER,
                "android.resource://com.openkin.hometraining/drawable/image_program_leg_beginner",
            ),
            MuscleGroup(
                "Пресс продолжающий",
                "20 мин · 16 упражнений",
                "Последний раз: окт.23.2024",
                TrainingLevel.CONTINUE,
                "android.resource://com.openkin.hometraining/drawable/image_program_press_continue",
            ),
            MuscleGroup(
                "Плечи и спина продолжающий",
                "20 мин · 12 упражнений",
                "",
                TrainingLevel.CONTINUE,
                "android.resource://com.openkin.hometraining/drawable/image_program_shoulders_continue",
            ),
            MuscleGroup(
                "Руки продолжающий",
                "20 мин · 16 упражнений",
                "15 мин · 10 упражнений",
                TrainingLevel.CONTINUE,
                "android.resource://com.openkin.hometraining/drawable/image_program_arm_continue",
            ),
            MuscleGroup(
                "Ноги продолжающий",
                "20 мин · 16 упражнений",
                "Последний раз: окт.23.2024",
                TrainingLevel.CONTINUE,
                "android.resource://com.openkin.hometraining/drawable/image_program_leg_continue",
            ),
            MuscleGroup(
                "Пресс продвинутый",
                "20 мин · 16 упражнений",
                "Последний раз: окт.23.2024",
                TrainingLevel.ADVANCED,
                "android.resource://com.openkin.hometraining/drawable/image_program_press_advanced",
            ),
            MuscleGroup(
                "Плечи и спина продвинутый",
                "20 мин · 12 упражнений",
                "",
                TrainingLevel.ADVANCED,
                "android.resource://com.openkin.hometraining/drawable/image_program_shoulders_advanced",
            ),
            MuscleGroup(
                "Руки продвинутый",
                "20 мин · 16 упражнений",
                "Последний раз: окт.23.2024",
                TrainingLevel.ADVANCED,
                "android.resource://com.openkin.hometraining/drawable/image_program_arm_advanced",
            ),
            MuscleGroup(
                "Ноги продвинутый",
                "20 мин · 12 упражнений",
                "",
                TrainingLevel.ADVANCED,
                "android.resource://com.openkin.hometraining/drawable/image_program_leg_advanced",
            ),
        ))
            .subscribeOn(Schedulers.io())
    }
}
