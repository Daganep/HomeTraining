package com.openkin.hometraining.domain.model

data class Goals(
    val goalsWanted: Int,
    val goalsDone: Int,
    val currentWeakL: List<Int>,
    val currentDayNumber: Int,
)
