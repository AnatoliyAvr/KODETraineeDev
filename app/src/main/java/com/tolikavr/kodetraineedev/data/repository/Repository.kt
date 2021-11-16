package com.tolikavr.kodetraineedev.data.repository

import com.tolikavr.kodetraineedev.data.models.UsersResponse
import com.tolikavr.kodetraineedev.data.network.NetworkResult
import kotlinx.coroutines.flow.Flow


interface Repository {

  suspend fun getUsers(): Flow<NetworkResult<UsersResponse>>
}