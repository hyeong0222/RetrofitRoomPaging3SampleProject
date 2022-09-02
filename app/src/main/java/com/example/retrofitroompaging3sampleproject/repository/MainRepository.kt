package com.example.retrofitroompaging3sampleproject.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.retrofitroompaging3sampleproject.database.dao.UserDao
import com.example.retrofitroompaging3sampleproject.model.User
import com.example.retrofitroompaging3sampleproject.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Singleton
class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
) {
    fun getSearchUserResultByPaging(searchQuery: String?): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 30),
            remoteMediator = UserRemoteMediator("jake", apiService, userDao)
        ) { UserPagingDataSource(searchQuery, apiService, userDao) }.flow
    }
}