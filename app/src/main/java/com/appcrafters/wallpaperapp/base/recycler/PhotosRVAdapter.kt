package com.appcrafters.retrofitapigg.base.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appcrafters.retrofitapigg.base.models.Photo
import com.appcrafters.wallpaperapp.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_photo.view.*
import kotlinx.android.synthetic.main.activity_main.*

class PhotosRVAdapter(
    private var photosList: List<Photo>,
    private val clickListener: (Photo) -> Unit
) : RecyclerView.Adapter<PhotosRVAdapter.PhotosRVViewHolder>() {


    class PhotosRVViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(photo: Photo, clickListener: (Photo) -> Unit) {
            Glide.with(itemView)
                .load(photo.urls.regular)
                .into(itemView.photo_src)

            itemView.setOnClickListener{
                clickListener(photo)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosRVViewHolder {
        return PhotosRVViewHolder(
            LayoutInflater.from(parent.context).
            inflate(R.layout.item_photo,parent,false)
        )
    }

    override fun onBindViewHolder(holder: PhotosRVViewHolder, position: Int) {
        holder.bind(photosList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    fun setData(newPhotosList: List<Photo>) {
        photosList = newPhotosList
        notifyDataSetChanged()

    }

}