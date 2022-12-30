package com.example.task4_vk

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.flexbox.*
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Response

class AlbumsActivity : AppCompatActivity() {
    private lateinit var errorCode: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        val albumsView: RecyclerView = findViewById(R.id.albumsList)
        errorCode = findViewById(R.id.albumsHead)

        val userId = intent.getStringExtra("user_id")!!

        albumsView.layoutManager = FlexboxLayoutManager(this@AlbumsActivity).apply {
            justifyContent = JustifyContent.SPACE_AROUND
            alignItems = AlignItems.CENTER
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
        }
        val adapter = AlbumsAdapter(userId, this@AlbumsActivity)
        albumsView.adapter = adapter

        uploadAlbums(adapter, userId, 0, 20)
    }

    fun uploadAlbums(
        adapter: AlbumsAdapter,
        userId: String,
        offset: Int,
        count: Int
    ) {
        lifecycleScope.launchWhenCreated {
            val api = RetrofitClient.getInstance().create(VkApi::class.java)
            var response: Response<GetAlbumResponse>? = null

            try {
                response = api.getAlbums(userId, offset, count, 1, 1)
                if (response.isSuccessful) {
                    if (response.body()?.response?.count == 0) {
                        errorCode.text = getString(R.string.noAlbumsWarning)
                        errorCode.visibility = View.VISIBLE
                    } else {
                        val albumItems: List<AlbumItem> = response.body()!!.response.items
                        adapter.addData(albumItems)
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