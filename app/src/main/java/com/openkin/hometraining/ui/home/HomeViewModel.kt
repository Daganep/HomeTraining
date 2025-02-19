package com.openkin.hometraining.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.openkin.hometraining.domain.interactor.IHomeDataInteractor

class HomeViewModel(private val homeDataInteractor: IHomeDataInteractor) : ViewModel() {

    private val homeScreenState: MutableLiveData<HomeScreenState> = MutableLiveData()
    val homeScreenData: LiveData<HomeScreenState> get() = homeScreenState

    init { loadData() }

    private fun loadData() {
        val statsRequest = homeDataInteractor.getStats()
        val goalsRequest = homeDataInteractor.getGoals()
        val programsRequest = homeDataInteractor.getPrograms()
        val muscleGroupsRequest = homeDataInteractor.getMuscleGroups()
    }
}
