package com.example.retrofitroompaging3sampleproject.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.retrofitroompaging3sampleproject.database.dao.UserDao
import com.example.retrofitroompaging3sampleproject.model.User
import com.example.retrofitroompaging3sampleproject.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
) {
    fun getSearchUserResultByPaging(searchQuery: String?): Flow<PagingData<User>> {
        return Pager(PagingConfig(pageSize = 30)) { UserPagingDataSource(searchQuery, apiService, userDao) }.flow
    }

    suspend fun insertUsers(users: List<User>) = userDao.insertUsers(users)
}