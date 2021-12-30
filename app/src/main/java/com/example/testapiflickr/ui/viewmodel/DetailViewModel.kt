package com.example.testapiflickr.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapiflickr.data.model.PhotoInfoModel
import com.example.testapiflickr.domain.GetInfoPhotoUseCase
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    var getInfoPhoto = GetInfoPhotoUseCase()
    var mutableInfoPhoto = MutableLiveData<PhotoInfoModel>()

    fun onCreate(id: String) {
        viewModelScope.launch {
            mutableInfoPhoto.postValue(getInfoPhoto(id))
        }
    }
}