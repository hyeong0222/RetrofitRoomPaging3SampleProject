package com.example.retrofitroompaging3sampleproject.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.retrofitroompaging3sampleproject.database.dao.UserDao
import com.example.retrofitroompaging3sampleproject.model.User
import com.example.retrofitroompaging3sampleproject.network.ApiService
import java.lang.Exception

class UserPagingDataSource(
    private val searchQuery: String?,
    private val apiService: ApiService,
    private val userDao: UserDao,
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val data = mutableListOf<User>()
            val page = params.key ?: 1

            runCatching {
                apiService.getSearchUserResult(searchQuery, page)
            }.onSuccess { searchUser ->
                searchUser?.users?.let {
                    data.addAll(it)
                }
            }.onFailure { error ->
                Log.e("", error.message.toString())
            }

            val prevKey = if (page == 1) null else page.minus(1)
            val nextKey = if (data.isEmpty()) null else page.plus(1)

            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}