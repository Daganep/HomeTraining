package com.openkin.hometraining.domain.model

data class MuscleGroup(
    val groupName: String,
    val description: String,
    val lastDate: String,
    val level: TrainingLevel,
    val groupImage: String, //TODO хранить в БД только название картинки, а базовую чать подставлять в маппере (android.resource://com.openkin.hometraining/drawable/)
)

enum class TrainingLevel() {
    BEGINNER,
    ADVANCED,
    PRO
}
