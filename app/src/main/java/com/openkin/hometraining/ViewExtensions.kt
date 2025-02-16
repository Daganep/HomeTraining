package com.openkin.hometraining

import android.view.LayoutInflater
import android.view.ViewGroup

fun <VB> createBinding(viewGroup: ViewGroup, inflate: Inflate<VB>): VB {
    return inflate.invoke(LayoutInflater.from(viewGroup.context), viewGroup, false)
}
