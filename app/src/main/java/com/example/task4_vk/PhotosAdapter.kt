package com.example.task4_vk

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView

class PhotosAdapter(
    private val userId: String,
    private val albumId: Int,
    private val context: Context
) :
    RecyclerView.Adapter<PhotosAdapter.PhotosHolder>() {

    private var photosUrl = ArrayList<String>()
    private var currentPhotosSize = 0
    var maxSize = 0
    var uploadingStatus = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosHolder {
        return PhotosHolder(LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false))
    }


    override fun onBindViewHolder(holder: PhotosHolder, position: Int) {
        Glide.with(context).load(photosUrl[position])
            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.photoImage)

        if (currentPhotosSize < maxSize && !uploadingStatus && position >= currentPhotosSize - 10) {
            uploadingStatus = true
            (context as PhotosActivity).uploadPictures(
                this,
                userId,
                photosUrl.size,
                50,
                albumId
            )
        }
    }

    inner class PhotosHolder(item: View) : RecyclerView.ViewHolder(item) {
        var photoImage: ImageView = item.findViewById(R.id.photoImage)
    }

    override fun getItemCount(): Int {
        return photosUrl.size
    }

    fun addData(photosUrl: List<String>) {
        val prevPhotosSize = this.photosUrl.size
        this.photosUrl += photosUrl
        currentPhotosSize = this.photosUrl.size
        notifyItemRangeInserted(prevPhotosSize, photosUrl.size)
    }
}