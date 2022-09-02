package com.example.retrofitroompaging3sampleproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.retrofitroompaging3sampleproject.R
import com.example.retrofitroompaging3sampleproject.databinding.ActivityMainBinding
import com.example.retrofitroompaging3sampleproject.ui.adapter.MainAdapter
import com.example.retrofitroompaging3sampleproject.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.adapter = adapter

        setListener()
    }

    private fun setListener() {
        lifecycleScope.launch {
            viewModel.getSearchUserResult("jake").collectLatest {
                adapter.submitData(it)
            }
        }
    }
}