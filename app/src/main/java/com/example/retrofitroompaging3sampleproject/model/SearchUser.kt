package com.example.retrofitroompaging3sampleproject.model

import com.google.gson.annotations.SerializedName

data class SearchUser(
    @SerializedName("items")
    val users: List<User>? = null,
)
