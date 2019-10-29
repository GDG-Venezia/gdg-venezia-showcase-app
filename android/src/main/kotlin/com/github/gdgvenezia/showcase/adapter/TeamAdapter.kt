package com.github.gdgvenezia.showcase.adapter;

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
import com.github.gdgvenezia.showcase.R

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

//            val testUrl =  "https://lh3.googleusercontent.com/sB_vqEkjJLRDW25oQI0jPCUbN2PTieoQ8oCmk9S2FaxZdjv12nk0kmNYM2ISiFx_yR23S-ptUGlpV5WG2Tg3-JyjGSBz9r1Pf8rZzPEZeVjjyMd5i-PShK-q20S6-1szck7PT8G-QZHCOuKSSqCgbCYGRSDZsjMe70n18I4msQdDDEz5vpoxYxiQG1RKkPwhs0bWQRPEqEBfFEcCtN36B-XTx2BlaBiB3aGcytkouNECSZQLO4z17fMS5ELEjpBBIhNIUQarwpd8K0WJqjQkN-5QYU4rokQCBsJmMEtbE1kl8HuRs_j5IH1PVjgHnRGfto28clSWk0xyohQllLFVrOl90XqUIBrCP_gHnGHJu_1xQKUKsY50pn-W6JK8jnRgpMWofT_Vq5jvEt9IrLPWgupltwRoEp2RL-JFGkK8ILrksSl7yJGuuqu5zhQ4lQz9ADu_NKemvLoAwslKmj6BiNcfwfcuou0RVEPzjiUrEV1rwJwVzrmMPeuD7gOSEROVn1Jf2Z5_e1xlE_ICHwnGJOlYup22kJYAi6EWQmeZ7074NcooJ1a39i_Eeh-KFRwX35T5FUsfsNZbpikowTEP73EIzuaeBizaYr-ryzIBGsPT6H4up-5WlLQkx3jVMKWn_qkPn3EAhV6FFW8ZUMzV4Ic7IwJOK8MFWWukdAu0RQ3JdenKW7jaa1s=w1806-h1798-no"
            Glide.with(itemView.context).load(item.pictureUrl).apply(RequestOptions.circleCropTransform()).into(imageView)
        }
    }
}
