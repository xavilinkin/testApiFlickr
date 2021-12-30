package com.example.testapiflickr.data

import com.example.testapiflickr.data.model.PhotoInfoModel
import com.example.testapiflickr.data.model.SearchModel
import com.example.testapiflickr.data.network.FlickrService

class FlickrRepository {

    private val api = FlickrService()

    suspend fun getSearchPhotos(idPhoto: String): SearchModel? {
        return api.getSearchPhotos(idPhoto)
    }

    suspend fun getInfoPhoto(id: String): PhotoInfoModel? {
        return api.getInfoPhoto(id)
    }
}