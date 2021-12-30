package com.example.testapiflickr.data.network

import com.example.testapiflickr.data.model.PhotoInfoModel
import com.example.testapiflickr.data.model.SearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface FlickrApiClient {
    @GET
    suspend fun getSearchPhotos(@Url url: String): Response<SearchModel>

    @GET
    suspend fun getInfoPhotos(@Url url: String): Response<PhotoInfoModel>
}