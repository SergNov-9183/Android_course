package com.example.task4_vk

import com.google.gson.annotations.SerializedName

class GetPhotosResponse(
    @SerializedName("response") var response: AlbumPhotosItem,
    @SerializedName("error") var error: ErrorsInfo
)
