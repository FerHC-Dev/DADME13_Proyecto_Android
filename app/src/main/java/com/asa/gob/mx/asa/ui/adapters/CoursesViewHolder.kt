package com.asa.gob.mx.asa.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.asa.gob.mx.asa.data.remote.model.CoursesDto
import com.asa.gob.mx.asa.databinding.CourseElementBinding
import com.bumptech.glide.Glide

class CoursesViewHolder(
    private val binding: CourseElementBinding
): RecyclerView.ViewHolder(binding.root) {

    val btnCourses = binding.btnCourses
    val btnCalendar = binding.btnCalendar

    fun bind(course: CoursesDto) {
        binding.tvTitleCourse.text = course.title

        println(binding.tvTitleCourse.text)
        /*
        notice.image?.apply {
            Glide.with(binding.root.context)
                .load(notice.image)
                .into(binding.ivThumbnail)
        }
        */
    }
}