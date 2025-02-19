package com.openkin.hometraining.domain.model

data class Goals(
    val goalsWanted: Int,
    val goalsDone: Int,
    val currentWeak: List<String>,
    val currentDayNumber: Int,
)
