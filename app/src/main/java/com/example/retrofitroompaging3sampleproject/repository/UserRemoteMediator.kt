package com.example.retrofitroompaging3sampleproject.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.retrofitroompaging3sampleproject.database.dao.UserDao
import com.example.retrofitroompaging3sampleproject.model.User
import com.example.retrofitroompaging3sampleproject.network.ApiService
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val searchQuery: String?,
    private val apiService: ApiService,
    private val userDao: UserDao,
): RemoteMediator<Int, User>(){
    override suspend fun load(loadType: LoadType, state: PagingState<Int, User>): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    lastItem.id
                }
            }

            val response = apiService.getSearchUserResult(searchQuery, loadKey?.toInt())

            response?.users?.let { userDao.insertUsers(it) }

            MediatorResult.Success(endOfPaginationReached = response?.users?.isEmpty() == true)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}