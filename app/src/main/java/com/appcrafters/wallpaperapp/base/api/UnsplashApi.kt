package com.appcrafters.retrofitapigg.base.api

import com.appcrafters.retrofitapigg.base.models.Photo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {

    @GET("/photos")
    suspend fun getPhotos(
        @Query("per_page") per_page: Int,
        @Query("client_id") key: String
    ) : Response<List<Photo>>

}