package com.appcrafters.retrofitapigg.base.repository

import com.appcrafters.retrofitapigg.base.api.RetrofitInstance
import com.appcrafters.retrofitapigg.base.models.Photo
import com.appcrafters.retrofitapigg.base.util.Constants.Companion.api_key
import retrofit2.Call
import retrofit2.Response

class Repository {

    suspend fun getPhotos(): Response<List<Photo>> {
        return RetrofitInstance.api.getPhotos(30, api_key)
    }

}