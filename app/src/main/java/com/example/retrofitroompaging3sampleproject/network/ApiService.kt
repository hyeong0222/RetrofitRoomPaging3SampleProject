package com.example.retrofitroompaging3sampleproject.network

import com.example.retrofitroompaging3sampleproject.model.SearchUser
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun getSearchUserResult(
        @Query("q") searchQuery: String?,
        @Query("page") page: Int?,
    ): SearchUser?
}