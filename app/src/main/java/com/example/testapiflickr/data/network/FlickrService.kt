package com.example.testapiflickr.data.network

import com.example.testapiflickr.constants.NetworkConstants.API_KEY
import com.example.testapiflickr.constants.NetworkConstants.PATH_SEARCH
import com.example.testapiflickr.constants.NetworkConstants.FORMAT
import com.example.testapiflickr.constants.NetworkConstants.NO_JSON_CALLBACK
import com.example.testapiflickr.core.RetrofitHelper
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
            response.body()
        }
    }
}