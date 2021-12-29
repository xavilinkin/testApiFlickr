package com.example.testapiflickr.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapiflickr.data.model.SearchModel
import com.example.testapiflickr.domain.GetSearchUseCase
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    var getSearch = GetSearchUseCase()
    var mutableSearch = MutableLiveData<SearchModel>()

    fun onCreate(idPhoto: String) {
        viewModelScope.launch {
            mutableSearch.postValue(getSearch(idPhoto))
        }
    }
}