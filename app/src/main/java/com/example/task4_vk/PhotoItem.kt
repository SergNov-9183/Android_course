package com.example.task4_vk

import com.google.gson.annotations.SerializedName

class PhotoItem(
    @SerializedName("sizes") var sizes: List<PhotoUrl>
)