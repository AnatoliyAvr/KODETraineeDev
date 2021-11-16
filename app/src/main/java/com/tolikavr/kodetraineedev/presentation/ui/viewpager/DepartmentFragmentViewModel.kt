package com.tolikavr.kodetraineedev.presentation.ui.viewpager

import androidx.lifecycle.*
import com.tolikavr.kodetraineedev.data.models.User
import com.tolikavr.kodetraineedev.data.models.UsersResponse
import com.tolikavr.kodetraineedev.data.network.NetworkResult
import com.tolikavr.kodetraineedev.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DepartmentFragmentViewModel(
  private val repository: Repository
) : ViewModel() {

  private val query = MutableLiveData<String>()
  private val _response: MutableLiveData<NetworkResult<UsersResponse>> = MutableLiveData()
  val response: LiveData<NetworkResult<UsersResponse>> = _response

  fun getAllUsers() = viewModelScope.launch(Dispatchers.IO) {
    repository.getUsers().collect {
      _response.postValue(it)
    }
  }

  fun getUserData(department: String): LiveData<List<User>> {
    val result = MediatorLiveData<List<User>>()

    val filter = {
      val users = _response.value?.data?.users.orEmpty()
      val queryStr = query.value ?: ""

      val userDepartment = if (department.isEmpty()) users else users.filter {
        it.department.contains(
          department,
          true
        )
      }

      result.value = if (queryStr.isEmpty()) userDepartment else userDepartment.filter {
        it.search().contains(
          queryStr,
          true
        )
      }
    }

    result.addSource(_response) { filter() }
    result.addSource(query) { filter() }
    return result
  }

  fun handleSearchQuery(text: String) {
    query.value = text
  }
}