package com.tolikavr.kodetraineedev.data.models


import com.google.gson.annotations.SerializedName


data class User(
  @SerializedName("avatarUrl")
  var avatarUrl: String,
  @SerializedName("birthday")
  var birthday: String,
  @SerializedName("department")
  var department: String,
  @SerializedName("firstName")
  var firstName: String,
  @SerializedName("id")
  var id: String,
  @SerializedName("lastName")
  var lastName: String,
  @SerializedName("phone")
  var phone: String,
  @SerializedName("position")
  var position: String,
  @SerializedName("userTag")
  var userTag: String
) {
  fun search() = "$firstName $lastName $userTag"
}