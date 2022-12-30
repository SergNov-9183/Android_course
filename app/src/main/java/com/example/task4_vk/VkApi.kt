package com.example.task4_vk

import retrofit2.Response
import retrofit2.http.Query
import retrofit2.http.GET

interface VkApi {
    @GET("photos.getAlbums")
    suspend fun getAlbums(
        @Query("owner_id") ownerId: String,
        @Query("offset") offset: Int,
        @Query("count") count: Int,
        @Query("need_system") needSystem: Int,
        @Query("need_covers") needCovers: Int
    ): Response<GetAlbumResponse>

    @GET("photos.get")
    suspend fun getPhotos(
        @Query("owner_id") ownerId: String,
        @Query("album_id") albumId: Int,
        @Query("offset") offset: Int,
        @Query("count") count: Int,
        @Query("photo_sizes") photoSizes: Int
    ): Response<GetPhotosResponse>
}