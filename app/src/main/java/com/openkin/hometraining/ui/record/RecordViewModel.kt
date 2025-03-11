package com.openkin.hometraining.ui.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.openkin.hometraining.domain.interactor.IHomeDataInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class RecordViewModel(private val homeDataInteractor: IHomeDataInteractor) : ViewModel() {

    private val subscriptions = CompositeDisposable()
    private val recordScreenState: MutableLiveData<RecordScreenState> = MutableLiveData()
    val recordScreenData: LiveData<RecordScreenState> get() = recordScreenState

    init {
        recordScreenState.value = RecordScreenState.LoadingState
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
                    recordScreenState.value = RecordScreenState.StatsLoaded(stats)
                }, { throwable ->
                    recordScreenState.value = RecordScreenState.ErrorState(throwable.message ?: "")
                })
        )
    }

    private fun getGoals() {
        subscriptions.add(
            homeDataInteractor.getGoals()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ goals ->
                    recordScreenState.value = RecordScreenState.GoalsLoaded(goals)
                }, { throwable ->
                    recordScreenState.value = RecordScreenState.ErrorState(throwable.message ?: "")
                })
        )
    }

    private fun getProgramsSevenFour() {
        subscriptions.add(
            homeDataInteractor.getProgramsSevenFour()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ programs ->
                    recordScreenState.value = RecordScreenState.ProgramsSevenFourLoaded(programs)
                }, { throwable ->
                    recordScreenState.value = RecordScreenState.ErrorState(throwable.message ?: "")
                })
        )
    }

    private fun getProgramsGroups() {
        subscriptions.add(
            homeDataInteractor.getProgramsGroups()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ groups ->
                    recordScreenState.value = RecordScreenState.ProgramsGroupsLoaded(groups)
                }, { throwable ->
                    recordScreenState.value = RecordScreenState.ErrorState(throwable.message ?: "")
                })
        )
    }
}
