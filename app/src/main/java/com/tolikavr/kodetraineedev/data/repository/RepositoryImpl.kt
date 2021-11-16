package com.tolikavr.kodetraineedev.data.repository

import com.tolikavr.kodetraineedev.data.models.ApiResponse
import com.tolikavr.kodetraineedev.data.models.UsersResponse
import com.tolikavr.kodetraineedev.data.network.NetworkResult
import com.tolikavr.kodetraineedev.data.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RepositoryImpl(
  private val networkService: NetworkService,
  private val apiResponse: ApiResponse
) : Repository {

  override suspend fun getUsers(): Flow<NetworkResult<UsersResponse>> {
    return flow {
      emit(apiResponse.safeApiCall { networkService.getUsers() })
    }.flowOn(Dispatchers.IO)
  }
}





