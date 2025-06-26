package com.asa.gob.mx.asa.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asa.gob.mx.asa.data.remote.model.ServicesDto
import com.asa.gob.mx.asa.databinding.ServicesElementBinding

class ServicesAdapter(
    private val services: List<ServicesDto>,
    private val onClickListener: (ServicesDto) -> Unit
): RecyclerView.Adapter<ServicesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val binding = ServicesElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServicesViewHolder(binding)
    }

    override fun getItemCount(): Int = services.size

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        val service = services[position]
        holder.bind(service)
        holder.itemView.setOnClickListener { onClickListener(service) }
    }
}