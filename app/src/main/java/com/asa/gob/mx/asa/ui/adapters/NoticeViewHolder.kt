package com.asa.gob.mx.asa.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.asa.gob.mx.asa.data.remote.model.NoticeDto
import com.asa.gob.mx.asa.databinding.NoticeElementBinding
import com.bumptech.glide.Glide

class NoticeViewHolder(
    private val binding: NoticeElementBinding
): RecyclerView.ViewHolder(binding.root) {

    val btnShowMore = binding.btnShowMore

    fun bind(notice: NoticeDto) {
        binding.tvTitleNotice.text = notice.title
        binding.tvDate.text = notice.date
        /*
        notice.image?.apply {
            Glide.with(binding.root.context)
                .load(notice.image)
                .into(binding.ivThumbnail)
        }
        */
    }
}