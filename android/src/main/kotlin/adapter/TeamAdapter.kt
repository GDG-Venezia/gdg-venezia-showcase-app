package org.konan.multiplatform.adapter;

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.gdgvenezia.domain.entities.TeamMemberModel
import org.konan.multiplatform.R

class TeamAdapter(var items: List<TeamMemberModel>) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(item: TeamMemberModel) {

            val titleText = itemView.findViewById<TextView>(R.id.ITEM_PEOPLE_title)
            val subtitleText = itemView.findViewById<TextView>(R.id.ITEM_PEOPLE_subtitle)
            val imageView = itemView.findViewById<ImageView>(R.id.ITEM_PEOPLE_image)

            titleText.text = "${item.firstname} ${item.lastname}"
            subtitleText.text = item.shortDescription

            // TODO: add a real url
            val testUrl =  "https://lh3.googleusercontent.com/Tw5oa9M6lcH8KhDP5f4fJtbFIjdimH8Zp50JJWWGED_V_B5kWtNiTF4EytRON2itQeLyYN9agZ6wdCPn5xYFyPxFshuB2LvCloKHo7iTROP9vDK4qxEf1XEvUpNm8KzpiAjzhTcDrxeBKmuDV3tsWKxAjh1DGuN7yogaUWPtxJZUwq-9mkjBueUiHbvAqOm5Mn6qws9GxIDPiPc3Fy9pjCF28PGSOvHhzog0M-HHUvrlUAhrWiHei1DB4S9Y326Hm1b8-hUgtJ9IMIIRa3gwtbqwLeFe1BMUI5K66xt78yxgg-pLYAYze1HMv3IfUviIVEipgq7M6UTWRcBLFzYJyqWMgRH05PmQoM9tqE-y854gk_ZcV3WN-7Dre_Y5Or_KZJgCQmdC2SuxG97s1GezQhFuNLuovTmObOsel2ctx2RF89f0fJ2QAW8RBoQnBy6vGuRwTVJc1ZQN6MMGkXO5aqsP9qWYQZ4B-vfAQmSZLgq4N7n3xeWi05M5Ym36E5WHb0FMMYArUCfDRPUDVUQK-fXTvAz64qE24g9VK5n9wlxqRru1y6nDe9qTQZWk05u56ETh-p-DeTI02R23msMZuISfgfSMsSFmuLYRPIYxDfWVfmYAai4MgQGNCltV7tqjg5XD3W8CCT2WlHq2RFFAGS67DA_-4sDq=w1870-h1862-no"
            Glide.with(itemView.context).load(testUrl).apply(RequestOptions.circleCropTransform()).into(imageView)
        }
    }
}
