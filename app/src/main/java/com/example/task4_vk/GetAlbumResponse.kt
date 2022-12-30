package com.example.task4_vk

import com.google.gson.annotations.SerializedName

class GetAlbumResponse(
    @SerializedName("response") var response: UserAlbums,
    @SerializedName("error") var error: ErrorsInfo
)