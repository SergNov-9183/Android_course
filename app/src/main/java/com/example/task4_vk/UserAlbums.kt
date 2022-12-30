package com.example.task4_vk

import com.google.gson.annotations.SerializedName

class UserAlbums(
    @SerializedName("count") var count: Int,
    @SerializedName("items") var items: List<AlbumItem>
)
