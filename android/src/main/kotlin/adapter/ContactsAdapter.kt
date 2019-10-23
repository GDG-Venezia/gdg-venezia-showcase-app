package org.konan.multiplatform.adapter;

import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.gdgvenezia.domain.entities.SocialLinkModel
import com.google.android.material.card.MaterialCardView
import org.konan.multiplatform.R
import android.content.Intent.ACTION_VIEW
import android.net.Uri


class ContactsAdapter(var items: List<SocialLinkModel>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_social, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: SocialLinkModel) {
            val card = itemView.findViewById<MaterialCardView>(R.id.ITEM_SOCIAL_card)
            val titleText = itemView.findViewById<TextView>(R.id.ITEM_SOCIAL_title)
            val imageView = itemView.findViewById<ImageView>(R.id.ITEM_SOCIAL_image)

            titleText.text = item.title
            getImageDrawable(item.code)?.let {
                imageView.setImageDrawable(it)
            }

            card.setOnClickListener {
                val browserIntent = Intent(ACTION_VIEW, Uri.parse(item.url))
                itemView.context.startActivity(browserIntent)
            }
        }

        private fun getImageDrawable(socialCode: String): Drawable? {
            val id = when (socialCode) {
                "facebook" -> R.drawable.ic_facebook
                "twitter" -> R.drawable.ic_twitter
                "instagram" -> R.drawable.ic_instagram
                "github" -> R.drawable.ic_github
                "youtube" -> R.drawable.ic_youtube
                "meetup" -> R.drawable.ic_meetup
                "telegram" -> R.drawable.ic_telegram
                "mail" -> R.drawable.ic_mail
                else -> -1
            }
            return if (id == -1) {
                null
            } else {
                ContextCompat.getDrawable(itemView.context, id)
            }
        }
    }
}
