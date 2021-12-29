package com.test.glexercise.injection.module


import com.test.glexercise.ui.mainList.MainListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


@JvmField
val AppModule = module {

    viewModel { MainListViewModel(get()) }
}