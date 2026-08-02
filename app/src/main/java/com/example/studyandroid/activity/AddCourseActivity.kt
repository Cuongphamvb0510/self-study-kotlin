package com.example.studyandroid.activity

import android.content.Intent
import android.os.Bundle
import com.example.studyandroid.R
import com.example.studyandroid.databinding.ActivityAddCourseBinding
import com.example.studyandroid.model.Course
import com.example.studyandroid.repository.CourseRepository
import com.example.studyandroid.view.MessageView

class AddCourseActivity : BaseActivity() {
    private lateinit var binding: ActivityAddCourseBinding
    private var editingCourseId: Int = -1

    private fun loadCourse(courseId: Int) {
        val course = CourseRepository.getCourseById(courseId) ?: return

        binding.edtTitle.setText(course.title)
        binding.edtPrice.setText(course.price)
        binding.edtLesson.setText(course.lesson.toString())
        binding.edtDuration.setText(course.duration)
        binding.edtRating.setText(course.rating.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applySystemBarInsets(binding.root)

        binding.btnAdd.setOnClickListener {
            if (!validateInput()) return@setOnClickListener


            if (editingCourseId == -1) {
                saveCourse()
            } else {
                updateCourse()
            }
        }

        //edit
        editingCourseId = intent.getIntExtra("courseId", -1)
        if (editingCourseId != -1) {
            loadCourse(editingCourseId)
            binding.btnAdd.text = "Cập nhật khóa học"
        } else {
            binding.btnAdd.text = "Thêm khoá học"
        }
    }

    private fun saveCourse() {
        val title = binding.edtTitle.text.toString().trim()
        val price = binding.edtPrice.text.toString().trim()
        val lesson = binding.edtLesson.text.toString().trim().toInt()
        val duration = binding.edtDuration.text.toString().trim()
        val rating = binding.edtRating.text.toString().trim().toFloatOrNull() ?: 0f

        val course = Course(
            id = CourseRepository.nextId(),
            image = R.mipmap.ic_launcher,
            title = title,
            price = price,
            rating = rating,
            totalReview = 0,
            lesson = lesson,
            duration = duration
        )

        CourseRepository.addCourse(course)
        MessageView.showSuccess(this, "Thêm khóa học thành công", 10000)
        finish()
    }

    private fun updateCourse() {
        val title = binding.edtTitle.text.toString().trim()
        val price = binding.edtPrice.text.toString().trim()
        val lesson = binding.edtLesson.text.toString().trim().toInt()
        val duration = binding.edtDuration.text.toString().trim()
        val rating = binding.edtRating.text.toString().trim().toFloatOrNull() ?: 0f

        val old = CourseRepository.getCourseById(editingCourseId) ?: return
        val course = Course(
            id = editingCourseId,
            image = old.image,
            title = title,
            price = price,
            rating = rating,
            totalReview = 0,
            lesson = lesson,
            duration = duration
        )

        CourseRepository.updateCourse(course)
        MessageView.showSuccess(this, "Cập nhật thành công")
        val homeIntent = Intent(this, MainActivity::class.java)
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(homeIntent)
        finish()
    }

    private fun validateInput(): Boolean {
        val title = binding.edtTitle.text.toString().trim()
        val price = binding.edtPrice.text.toString().trim()
        val lession = binding.edtLesson.text.toString().trimIndent()
        val duration = binding.edtDuration.text.toString().trim()
        val rating = binding.edtRating.text.toString().trim()
        if (title.isEmpty()) {
            binding.edtTitle.error = "Vui lòng nhập tên khoá hoc"
            binding.edtTitle.requestFocus()
            return false
        }
        if (title.length < 3 || title.length > 200) {
            binding.edtTitle.error = "Ten khoa hoc co it nhat 3 ki tu va nhieu nhat 200 ky tu"
            binding.edtTitle.requestFocus()
            return false
        }
        if (price.isEmpty()) {
            binding.edtPrice.error = "Vui lòng nhập giá khóa học"
            binding.edtPrice.requestFocus()
            return false
        }

        if (lession.isEmpty()) {
            binding.edtLesson.error = "jhdfjshdfks"
            binding.edtLesson.requestFocus()
            return false
        }

        val numberLession = lession.toIntOrNull()

        if (numberLession == null) {
            binding.edtLesson.error = "phai la so nguyen"
            binding.edtLesson.requestFocus()
            return false
        }
        if (numberLession <= 0) {
            binding.edtLesson.error = "phai la so lon hon 0"
            binding.edtLesson.requestFocus()
            return false
        }

        return true
    }
}