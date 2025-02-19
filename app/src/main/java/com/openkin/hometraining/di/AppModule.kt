package com.openkin.hometraining.di

import com.openkin.hometraining.data.HomeDataRepository
import com.openkin.hometraining.domain.interactor.HomeDataInteractor
import com.openkin.hometraining.domain.interactor.IHomeDataInteractor
import com.openkin.hometraining.domain.interactor.IHomeDataRepository
import com.openkin.hometraining.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<IHomeDataInteractor> { HomeDataInteractor() }

    single<IHomeDataRepository> { HomeDataRepository() }

    viewModel { HomeViewModel(homeDataInteractor = get()) }
}

