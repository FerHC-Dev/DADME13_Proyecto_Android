package com.asa.gob.mx.asa.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asa.gob.mx.asa.data.remote.model.NoticeDto
import com.asa.gob.mx.asa.databinding.NoticeElementBinding

class NoticeAdapter(
    private val notices: List<NoticeDto>,
    private val onClickListener: (NoticeDto) -> Unit
): RecyclerView.Adapter<NoticeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = NoticeElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(binding)
    }

    override fun getItemCount(): Int = notices.size

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        val notice = notices[position]
        holder.bind(notice)
        holder.btnShowMore.setOnClickListener { onClickListener(notice) }
        //holder.itemView.setOnClickListener { onClickListener(notice) }
    }
}