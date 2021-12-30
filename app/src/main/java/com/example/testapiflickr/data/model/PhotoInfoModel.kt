package com.example.testapiflickr.data.model

import com.google.gson.annotations.SerializedName

data class PhotoInfoModel(@SerializedName("photo") val photo: ContentModel?)

data class ContentModel(
    @SerializedName("owner") val owner: OwnerModel,
    @SerializedName("title") val title: TitleModel,
    @SerializedName("description") val description: DescriptionModel,
    @SerializedName("dates") val dates: DatesModel
)

data class OwnerModel(@SerializedName("username") val username: String)

data class TitleModel(@SerializedName("_content") val _content: String)

data class DescriptionModel(@SerializedName("_content") val _content: String)

data class DatesModel(@SerializedName("taken") val taken: String)