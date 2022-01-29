package com.appcrafters.retrofitapigg

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appcrafters.retrofitapigg.base.models.Photo
import com.appcrafters.retrofitapigg.base.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class MainViewModel(
    private val repository: Repository
): ViewModel() {

    val photoResponse: MutableLiveData<Response<List<Photo>>> = MutableLiveData()

    fun getPhotos() {
        viewModelScope.launch {
            val response = repository.getPhotos()
            photoResponse.value = response

        }
    }

}