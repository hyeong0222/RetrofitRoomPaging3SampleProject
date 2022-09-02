package com.example.retrofitroompaging3sampleproject.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.retrofitroompaging3sampleproject.model.User
import com.example.retrofitroompaging3sampleproject.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    fun getSearchUserResult(searchQuery: String?): Flow<PagingData<User>> {
        return repository.getSearchUserResultByPaging(searchQuery).cachedIn(viewModelScope)
    }
}