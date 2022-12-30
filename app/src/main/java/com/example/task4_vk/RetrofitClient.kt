package com.example.task4_vk

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val apiVkToken = "vk1.a.Y2c3-xzFU-LwNpKRy5R5mc77Z1Zu_ixnIJzEXQLqmMfEqLWKcBrv9qVLG8O19ojmMEYckrZH6nudjWowzWVPWC2gb0fEGrJSPSOcP7IzUNoZVbVVH-Vb5WM-uxdGFGzj13XNIBxomup9gfYeBQ6mIf2uw5p2rugdHpMEIZ-vOeUjaZXlwUgFLGCV0PeDED_Ah1YoKTXDAplkUUNZUC8mmQ"
    private const val version = "5.131"


        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.vk.com/method/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            val url = chain
                                .request()
                                .url()
                                .newBuilder()
                                .addQueryParameter("v", version)
                                .addQueryParameter("access_token", apiVkToken)
                                .build()
                            chain.proceed(chain.request().newBuilder().url(url).build())
                        }
                        .build()
                )
                .build()
        }
    }