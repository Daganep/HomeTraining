package com.openkin.hometraining.domain.model

import android.net.Uri

data class ProgramSevenFour(
    val programName: String,
    val donePercent: Int,
    val currentDay: Int,
    val description: String,
    val image: Uri,
)
