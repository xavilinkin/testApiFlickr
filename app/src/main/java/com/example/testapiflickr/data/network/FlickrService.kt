package com.example.testapiflickr.data.network

import com.example.testapiflickr.constants.NetworkConstants.API_KEY
import com.example.testapiflickr.constants.NetworkConstants.PATH_SEARCH
import com.example.testapiflickr.constants.NetworkConstants.FORMAT
import com.example.testapiflickr.constants.NetworkConstants.NO_JSON_CALLBACK
import com.example.testapiflickr.constants.NetworkConstants.PATH_INFO
import com.example.testapiflickr.core.RetrofitHelper
import com.example.testapiflickr.data.model.ListSearch
import com.example.testapiflickr.data.model.PhotoInfoModel
import com.example.testapiflickr.data.model.SearchModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlickrService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getSearchPhotos(idPhoto: String): SearchModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(FlickrApiClient::class.java).getSearchPhotos(
                "?method=" + PATH_SEARCH + "&api_key=" + API_KEY + "&tags=" + idPhoto +
                        "&format=" + FORMAT + "&nojsoncallback=" + NO_JSON_CALLBACK
            )
            if (response.isSuccessful) {
                response.body()
            } else {
                SearchModel(ListSearch(null))
            }
        }
    }

    suspend fun getInfoPhoto(id: String): PhotoInfoModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(FlickrApiClient::class.java).getInfoPhotos(
                "?method=" + PATH_INFO + "&api_key=" + API_KEY + "&photo_id=" + id +
                        "&format=" + FORMAT + "&nojsoncallback=" + NO_JSON_CALLBACK
            )
            if (response.isSuccessful) {
                response.body()
            } else {
                PhotoInfoModel(null)
            }
        }
    }
}