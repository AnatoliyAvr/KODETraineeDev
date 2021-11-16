package com.tolikavr.kodetraineedev.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tolikavr.kodetraineedev.data.models.UsersResponse
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface NetworkService {

  @GET("users")
  suspend fun getUsers(): Response<UsersResponse>

  @ExperimentalSerializationApi
  companion object {
    operator fun invoke(): NetworkService {

      val logging = HttpLoggingInterceptor()
      logging.level = HttpLoggingInterceptor.Level.BODY

      val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

      return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://stoplight.io/mocks/kode-education/trainee-test/25143926/")
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetworkService::class.java)
    }
  }
}