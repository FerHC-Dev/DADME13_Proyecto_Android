package com.asa.gob.mx.asa.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.asa.gob.mx.asa.data.remote.model.ServicesDto
import com.asa.gob.mx.asa.databinding.ServicesElementBinding
import com.bumptech.glide.Glide

class ServicesViewHolder(
    private val binding: ServicesElementBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(services: ServicesDto) {
        binding.tvTitleNotice.text = services.title
        /*
        Glide.with(binding.root.context)
            .load(services.image)
            .into(binding.ivThumbnail)
         */
    }

}