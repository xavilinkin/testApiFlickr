package com.example.testapiflickr.domain

import com.example.testapiflickr.data.FlickrRepository
import com.example.testapiflickr.data.model.SearchModel

class GetSearchUseCase {
    private val repository = FlickrRepository()

    suspend operator fun invoke(idPhoto: String): SearchModel? {
        return repository.getSearchPhotos(idPhoto)
    }
}