package com.example.studyandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyandroid.databinding.ItemCourseBinding
import com.example.studyandroid.listener.OnCourseClickListener
import com.example.studyandroid.model.Course

class CourseAdapter(
    private val courseList: List<Course>,
    private val listener: OnCourseClickListener
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    class CourseViewHolder(
        val binding: ItemCourseBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {

        val binding = ItemCourseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CourseViewHolder,
        position: Int
    ) {
        val course = courseList[position]

        holder.binding.imgCourse.setImageResource(course.image)

        holder.binding.txtTitle.text = course.title

        holder.binding.txtPrice.text = course.price

        holder.binding.ratingBar.rating = course.rating

        holder.binding.txtRating.text =
            "${course.rating} (${course.totalReview})"

        holder.binding.txtLesson.text =
            "${course.lesson} Lessons"

        holder.binding.txtDuration.text =
            course.duration

        holder.itemView.setOnClickListener {

            listener.onCourseClick(course)

        }
    }

    override fun getItemCount(): Int {
        return courseList.size
    }
}