package com.axxess.imageapp.adapter

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axxess.imageapp.ImageCommentsActivity
import com.axxess.imageapp.R
import com.axxess.imageapp.constants.Constants.IMAGE_SELECTED_KEY
import com.axxess.imageapp.extensions.inflate
import com.axxess.imageapp.models.ImageEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_list_row_view.view.*

class ImageAdapter() : RecyclerView.Adapter<ImageAdapter.ImageHolder>() {

    var imageEntities: List<ImageEntity> = listOf()

    override fun onBindViewHolder(holder: ImageAdapter.ImageHolder, position: Int) {
        val itemPhoto = imageEntities[position]
        holder.bindPhoto(itemPhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val inflatedView = parent.inflate(R.layout.image_list_row_view, false)
        return ImageHolder(inflatedView)
    }

    override fun getItemCount() = imageEntities.size

    fun setImageList(imageEntityList: List<ImageEntity>) {
        imageEntities = imageEntityList
    }

    class ImageHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v
        private var photo: ImageEntity? = null


        fun bindPhoto(photo: ImageEntity) {
            this.photo = photo
            Picasso.with(view.context).load(photo.link).into(view.itemImage)
            view.setOnClickListener {
                Log.d("RecyclerView", "CLICK!")
                val context = itemView.context
                val showPhotoIntent = Intent(context, ImageCommentsActivity::class.java)
                showPhotoIntent.putExtra(IMAGE_SELECTED_KEY, photo)
                context.startActivity(showPhotoIntent)
            }
        }
    }
}