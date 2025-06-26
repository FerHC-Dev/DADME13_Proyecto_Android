package com.asa.gob.mx.asa.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asa.gob.mx.asa.data.remote.model.CoursesDto
import com.asa.gob.mx.asa.databinding.CourseElementBinding

class CoursesAdapter(
    private val notices: List<CoursesDto>,
    private val onCalendarListener: (CoursesDto) -> Unit,
    private val onCourseListener: (CoursesDto) -> Unit
): RecyclerView.Adapter<CoursesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        val binding = CourseElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoursesViewHolder(binding)
    }

    override fun getItemCount(): Int = notices.size

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        val course = notices[position]
        holder.bind(course)
        holder.btnCourses.setOnClickListener { onCourseListener(course) }
        holder.btnCalendar.setOnClickListener { onCalendarListener(course) }
        //holder.itemView.setOnClickListener { onClickListener(notice) }
    }

}