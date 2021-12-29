package com.example.testapiflickr.data.model

import com.google.gson.annotations.SerializedName

data class SearchModel(@SerializedName("photos") val photos: ListSearch?)

data class ListSearch(@SerializedName("photo") val photo: List<ItemPhoto>)

data class ItemPhoto(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("secret") val secret: String,
    @SerializedName("server") val server: String
)