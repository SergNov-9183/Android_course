package com.example.task4_vk

import com.google.gson.annotations.SerializedName

data class ErrorsInfo(
    @SerializedName("error_code") val code: Int
)
