package com.tolikavr.kodetraineedev.presentation.app

import android.app.Application
import com.tolikavr.kodetraineedev.presentation.di.appModule
import com.tolikavr.kodetraineedev.presentation.di.dataModule
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


@ExperimentalSerializationApi
class App : Application() {

  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidLogger(Level.DEBUG)
      androidContext(this@App)
      modules(listOf(appModule, dataModule))
    }
  }
}