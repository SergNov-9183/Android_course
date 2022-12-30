package com.example.task4_vk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Response
import com.google.android.flexbox.*


class PhotosActivity : AppCompatActivity() {
    private lateinit var errorCode: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        val photosList: RecyclerView = findViewById(R.id.photosList)
        errorCode = findViewById(R.id.photosHead)

        val albumId: Int = intent.getIntExtra("album_id", -1)
        val albumName: String = intent.getStringExtra("album_name")!!
        val userId: String = intent.getStringExtra("user_id")!!

        title = albumName

        photosList.layoutManager = FlexboxLayoutManager(this@PhotosActivity).apply {
            justifyContent = JustifyContent.CENTER
            alignItems = AlignItems.CENTER
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
        }
        val adapter = PhotosAdapter(userId, albumId, this@PhotosActivity)
        photosList.adapter = adapter

        uploadPictures(adapter, userId, 0, 50, albumId)
    }

    fun uploadPictures(
        adapter: PhotosAdapter,
        userId: String,
        offset: Int,
        count: Int,
        albumId: Int,
    ) {
        lifecycleScope.launchWhenCreated {
            val api = RetrofitClient.getInstance().create(VkApi::class.java)
            var response: Response<GetPhotosResponse>? = null

            try {
                response = api.getPhotos(
                    userId,
                    albumId,
                    offset,
                    count,
                    1
                )
                if (response.isSuccessful) {
                    if (response.body()?.response?.count == 0) {
                        errorCode.text = getString(R.string.noPhotosWarning)
                        errorCode.visibility = View.VISIBLE
                    } else {
                        val photoItems: List<PhotoItem> = response.body()!!.response.items
                        val photosUrl = ArrayList<String>()
                        for (photo in photoItems) {
                            photosUrl.add(photo.sizes[6].url)
                        }

                        adapter.addData(photosUrl)
                        adapter.uploadingStatus = false
                        adapter.maxSize = response.body()!!.response.count
                    }
                } else {
                    errorCode.text = getString(R.string.internetConnectionError)
                    errorCode.visibility = View.VISIBLE
                }
            } catch (Ex: Exception) {
                val code = response?.body()?.error?.code
                when (code) {
                    5 -> errorCode.text = getString(R.string.tokenExpiredError)
                    6 -> errorCode.text = getString(R.string.tooManyRequestsError)
                    10 -> errorCode.text = getString(R.string.serverError)
                    15 -> errorCode.text = getString(R.string.permissionDeniedError)
                    18 -> errorCode.text = getString(R.string.userBlockedError)
                    100 -> errorCode.text = getString(R.string.incorrectParamError)
                    200 -> errorCode.text = getString(R.string.albumPermissionDeniedError)
                    1116 -> errorCode.text = getString(R.string.invalidTokenError)
                    else -> errorCode.text = getString(R.string.unknownError)
                }
                errorCode.visibility = View.VISIBLE
            }
        }
    }
}