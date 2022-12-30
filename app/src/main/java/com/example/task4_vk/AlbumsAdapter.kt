package com.example.task4_vk

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class AlbumsAdapter(private val userId: String, private val context: Context) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumsHolder>() {

    private var albumItems = ArrayList<AlbumItem>()
    private var currentAlbumsSize = 0
    var maxSize = 0
    var uploadingStatus = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsHolder {
        return AlbumsHolder(LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false))
    }

    override fun onBindViewHolder(holder: AlbumsHolder, position: Int) {
        holder.albumName.text = albumItems[position].title
        holder.albumImage.setOnClickListener(openAlbum(position))
        holder.albumCard.setOnClickListener(openAlbum(position))

        Glide.with(context).load(albumItems[position].thumbSrc)
            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.albumImage)

        if (currentAlbumsSize < maxSize && !uploadingStatus && position >= currentAlbumsSize - 5) {
            uploadingStatus = true
            (context as AlbumsActivity).uploadAlbums(this, userId, albumItems.size, 20)
        }
    }

    inner class AlbumsHolder(item: View) : RecyclerView.ViewHolder(item) {
        var albumName: TextView = item.findViewById(R.id.емФlbumТame)
        var albumCard: CardView = item.findViewById(R.id.album_card)
        var albumImage: ImageButton = item.findViewById(R.id.btnAlbumImage)
    }

    private fun openAlbum(position: Int): (v: View) -> Unit = {
        val intent = Intent(context, PhotosActivity::class.java)
        intent.putExtra("album_id", albumItems[position].id)
        intent.putExtra("album_name", albumItems[position].title)
        intent.putExtra("user_id", userId)
        context.startActivity(intent)
    }

    fun addData(albumItems: List<AlbumItem>) {
        val previousAlbumsSize = this.albumItems.size
        this.albumItems += albumItems
        currentAlbumsSize = this.albumItems.size
        notifyItemRangeInserted(previousAlbumsSize, albumItems.size)
    }

    override fun getItemCount(): Int {
        return albumItems.size
    }
}