package com.tolikavr.kodetraineedev.presentation.di

import com.tolikavr.kodetraineedev.data.models.ApiResponse
import com.tolikavr.kodetraineedev.data.network.NetworkService
import com.tolikavr.kodetraineedev.data.repository.Repository
import com.tolikavr.kodetraineedev.data.repository.RepositoryImpl
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.dsl.module


@ExperimentalSerializationApi
val dataModule = module {

  fun network() = NetworkService.invoke()

  single { ApiResponse() }
  single<Repository> { RepositoryImpl(networkService = get(), apiResponse = get()) }
  single { network() }
}