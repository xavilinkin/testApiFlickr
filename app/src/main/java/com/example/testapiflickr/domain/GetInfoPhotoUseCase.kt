package com.example.testapiflickr.domain

import com.example.testapiflickr.data.FlickrRepository
import com.example.testapiflickr.data.model.PhotoInfoModel

class GetInfoPhotoUseCase {
    private val repository = FlickrRepository()

    suspend operator fun invoke(id: String): PhotoInfoModel? {
        return repository.getInfoPhoto(id)
    }
}