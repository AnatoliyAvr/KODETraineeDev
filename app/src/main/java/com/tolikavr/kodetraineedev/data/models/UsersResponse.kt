package com.tolikavr.kodetraineedev.data.models


import com.google.gson.annotations.SerializedName


data class UsersResponse(
  @SerializedName("items")
  var users: List<User>
)