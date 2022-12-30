package com.example.task4_vk

import com.google.gson.annotations.SerializedName

class AlbumItem(
    @SerializedName("id") var id: Int,
    @SerializedName("owner_id") var ownerId: Int,
    @SerializedName("title") var title: String,
    @SerializedName("thumb_id") var thumbId: Int,
    @SerializedName("thumb_src") var thumbSrc: String,
)
