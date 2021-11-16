package com.tolikavr.kodetraineedev.presentation.di

import com.tolikavr.kodetraineedev.presentation.ui.viewpager.DepartmentFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
  viewModel {
    DepartmentFragmentViewModel(
      repository = get()
    )
  }
}