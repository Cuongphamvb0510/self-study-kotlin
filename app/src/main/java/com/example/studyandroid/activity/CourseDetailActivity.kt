package com.example.studyandroid.activity

import android.content.Intent
import android.os.Bundle
import com.example.studyandroid.databinding.ActivityCourseDetailBinding
import com.example.studyandroid.repository.CourseRepository
import com.example.studyandroid.view.MessageView

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


        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, AddCourseActivity::class.java)
            intent.putExtra("courseId", courseId)
            startActivity(intent)
        }

        binding.btnDelete.setOnClickListener {
            android.app.AlertDialog.Builder(this).setTitle("Xoá khoá học")
                .setMessage("Bạn có chắc chắn muốn xoá khhoá học")
                .setPositiveButton("Xóa") { _, _ ->
                    CourseRepository.deleteCourse(courseId)
                    MessageView.showSuccess(this, "Đã xóa khóa học")
                    val homeIntent = Intent(this, MainActivity::class.java)
                    homeIntent.addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    )
                    startActivity(homeIntent)
                    finish()
                }
                .setNegativeButton("Hủy", null)
                .show()
        }
    }
}