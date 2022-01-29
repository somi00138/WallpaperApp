package com.appcrafters.retrofitapigg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appcrafters.retrofitapigg.base.repository.Repository

class MainViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}