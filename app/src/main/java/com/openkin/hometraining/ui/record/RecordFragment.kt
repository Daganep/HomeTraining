package com.openkin.hometraining.ui.record

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.openkin.hometraining.BaseFragment
import com.openkin.hometraining.R
import com.openkin.hometraining.databinding.FragmentRecordBinding
import com.openkin.hometraining.domain.model.HomeStats
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecordFragment : BaseFragment<FragmentRecordBinding>(FragmentRecordBinding::inflate) {

    private val viewModel by viewModel<RecordViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observeDataState()
    }

    private fun initUi() {
        binding?.let {
            it.recordStats.trainingsStatsIcon.isVisible = true
            it.recordStats.caloriesStatsIcon.isVisible = true
            it.recordStats.minutesStatsIcon.isVisible = true
        }
    }

    private fun observeDataState() {
        viewModel.recordScreenData.observe(viewLifecycleOwner) { state ->
            when(state) {
                is RecordScreenState.StatsLoaded -> updateStats(state.statsData)
                is RecordScreenState.GoalsLoaded -> {  }
                is RecordScreenState.ProgramsSevenFourLoaded -> {  }
                is RecordScreenState.ProgramsGroupsLoaded -> {  }
                is RecordScreenState.LoadingState -> Log.d("MyFilter", "Loading in progress!")
                is RecordScreenState.ErrorState -> Log.e("Errors", state.message)
            }
        }
    }

    private fun updateStats(statsData: HomeStats) {
        binding?.recordStats?.let {
            it.trainingsStatsIcon.isVisible = true
            val trainingNumber = statsData.trainingNumber
            it.trainingsNumber.text = trainingNumber.toString()
            it.trainingsLabel.text =
                resources.getQuantityString(R.plurals.stats_trainings, statsData.trainingNumber)
            it.caloriesNumber.text = statsData.caloriesNumber.toString()
            it.minutesNumber.text = statsData.trainingsMinutes.toString()
            it.root.setOnClickListener { clickIndicator() }
        }
    }

    private fun clickIndicator(text: String = "") {
        Toast.makeText(activity, "Clicked$text", Toast.LENGTH_SHORT).show()
    }
}
