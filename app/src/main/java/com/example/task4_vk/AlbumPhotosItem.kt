package com.example.task4_vk

import com.google.gson.annotations.SerializedName

class AlbumPhotosItem(
    @SerializedName("count") var count: Int,
    @SerializedName("items") var items: List<PhotoItem>
)
