package com.openkin.hometraining.domain.model

data class MuscleGroup(
    val groupName: String,
    val description: String,
    val lastDate: String,
    val level: TrainingLevel,
    val groupImage: String,
)

enum class TrainingLevel() {
    BEGINNER,
    ADVANCED,
    PRO
}
