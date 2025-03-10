package com.openkin.hometraining.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.openkin.hometraining.domain.interactor.IHomeDataInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(private val homeDataInteractor: IHomeDataInteractor) : ViewModel() {

    private val subscriptions = CompositeDisposable()
    private val homeScreenState: MutableLiveData<HomeScreenState> = MutableLiveData()
    val homeScreenData: LiveData<HomeScreenState> get() = homeScreenState

    init {
        homeScreenState.value = HomeScreenState.LoadingState
        getStats()
        getGoals()
        getProgramsSevenFour()
        getProgramsGroups()
    }

    private fun getStats() {
        subscriptions.add(
            homeDataInteractor.getStats()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ stats ->
                    homeScreenState.value = HomeScreenState.StatsLoaded(stats)
                }, { throwable ->
                    homeScreenState.value = HomeScreenState.ErrorState(throwable.message ?: "")
                })
        )
    }

    private fun getGoals() {
        subscriptions.add(
            homeDataInteractor.getGoals()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ goals ->
                    homeScreenState.value = HomeScreenState.GoalsLoaded(goals)
                }, { throwable ->
                    homeScreenState.value = HomeScreenState.ErrorState(throwable.message ?: "")
                })
        )
    }

    private fun getProgramsSevenFour() {
        subscriptions.add(
            homeDataInteractor.getProgramsSevenFour()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ programs ->
                    homeScreenState.value = HomeScreenState.ProgramsSevenFourLoaded(programs)
                }, { throwable ->
                    homeScreenState.value = HomeScreenState.ErrorState(throwable.message ?: "")
                })
        )
    }

    private fun getProgramsGroups() {
        subscriptions.add(
            homeDataInteractor.getProgramsGroups()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ groups ->
                    homeScreenState.value = HomeScreenState.ProgramsGroupsLoaded(groups)
                }, { throwable ->
                    homeScreenState.value = HomeScreenState.ErrorState(throwable.message ?: "")
                })
        )
    }
}
