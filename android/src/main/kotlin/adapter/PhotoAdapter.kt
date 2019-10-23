package org.konan.multiplatform.adapter;

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.gdgvenezia.domain.entities.PhotoModel
import org.konan.multiplatform.R

class PhotoAdapter(var items: List<PhotoModel>) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: PhotoModel) {
            val imageView = itemView.findViewById<ImageView>(R.id.ITEM_IMAGE_image)

            Glide.with(itemView.context)
                    .load(item.url)
                    .dontTransform()
                    .placeholder(ColorDrawable(ContextCompat.getColor(itemView.context, R.color.white)))
                    .into(imageView)

        }
    }
}
