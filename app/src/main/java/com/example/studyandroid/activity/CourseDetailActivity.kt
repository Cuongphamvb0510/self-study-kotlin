package com.example.studyandroid.activity

import CourseRepository
import android.os.Bundle
import com.example.studyandroid.databinding.ActivityCourseDetailBinding

class CourseDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityCourseDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCourseDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        applySystemBarInsets(binding.root)

        val courseId = intent.getIntExtra("courseId", -1)
        val course = CourseRepository.getCourseById(courseId)

        binding.txtTitle.text = course?.title
        binding.txtPrice.text = course?.price
        binding.txtLesson.text = "${course?.lesson} Lessons"
        binding.txtDuration.text = course?.duration
        binding.txtRating.text = "⭐ ${course?.rating}"

        course?.let {
            binding.imgCourse.setImageResource(it.image)
        }
    }
}